package com.yh.page;

import com.yh.service.IDALService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DALByPage {
    private static final Logger logger = Logger.getLogger(DALByPage.class);
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public DALByPage() {
    }

    public static List<Map<String, Object>> searchByPage(IDALService dalService, PageParam pageParam) {
        ArrayList result = new ArrayList();
        String countSql;
        if(StringUtils.isNotBlank(pageParam.getCountSql())) {
            countSql = pageParam.getCountSql();
        } else {
            countSql = getCountSql(pageParam.getSqlParam().getSql());
        }

        Map countResult = dalService.find(new SqlParam(countSql, pageParam.getSqlParam().getParams()));
        int totalRecord = Integer.parseInt(countResult.values().toArray()[0].toString());
        pageParam.setTotalRecord(totalRecord);
        logger.info("当前SQL[" + countSql + "] 记录总数为 " + totalRecord);
        if(totalRecord < 1) {
            return result;
        } else {
            int pageSize = pageParam.getBatchSize().intValue();
            int totalPage = totalRecord % pageSize == 0?totalRecord / pageSize:totalRecord / pageSize + 1;
            ArrayList tasks = new ArrayList();

            for(int task = 0; task < totalPage; ++task) {
                tasks.add(executor.submit(new DALByPage.PageTask(dalService, getPageSql(task + 1, pageSize, pageParam.getSqlParam().getSql()), pageParam.getSqlParam().getParams())));
            }

          /*  Iterator var10 = tasks.iterator();

            while(var10.hasNext()) {
                Future var15 = (Future)var10.next();

                try {
                    result.addAll((Collection)var15.get());
                } catch (InterruptedException var12) {
                    logger.error(var12.getMessage(), var12);
                } catch (ExecutionException var13) {
                    logger.error(var13.getMessage(), var13);
                } catch (Exception var14) {
                    logger.error(var14.getMessage(), var14);
                }
            }*/

            return result;
        }
    }

    public static void exportData(IDALService dalService, PageParam pageParam, FileParam fileParam) {
        List data = searchByPage(dalService, pageParam);
        exportData(data, fileParam);
    }

    public static void exportData(List<Map<String, Object>> data, FileParam fileParam) {
       /* int totalRecord = data.size();
        int lineSize = fileParam.getLineSize();
        int totalPage = totalRecord % lineSize == 0?totalRecord / lineSize:totalRecord / lineSize + 1;
        StringBuilder[] sbs = new StringBuilder[totalPage];

        for(int keys = 0; keys < totalPage; ++keys) {
            sbs[keys] = new StringBuilder();
        }

        Set var13 = null;

        int i;
        for(i = 0; i < totalRecord; ++i) {
            Map e = (Map)data.get(i);
            if(i == 0) {
                var13 = e.keySet();
                StringBuilder key = new StringBuilder();
                Iterator var11 = var13.iterator();

                while(var11.hasNext()) {
                    String j = (String)var11.next();
                    key.append(j).append(",");
                }

                key.append("\n");

                for(int var15 = 0; var15 < totalPage; ++var15) {
                    sbs[var15].append(key.toString());
                }
            }

            Iterator var16 = var13.iterator();

            while(var16.hasNext()) {
                String var14 = (String)var16.next();
                sbs[i / lineSize].append(e.get(var14)).append(",");
            }

            sbs[i / lineSize].append("\n");
        }

        for(i = 0; i < totalPage; ++i) {
            try {
                FileUtils.writeStringToFile(new File(fileParam.getFilePath() + "/" + fileParam.getFileName() + "_" + i + ".csv"), sbs[i].toString(), "gbk");
            } catch (IOException var12) {
                logger.error(var12.getMessage(), var12);
            }
        }
*/
    }

    private static String getPageSql(int page, int pageSize, String sql) {
        StringBuilder sqlBuilder = new StringBuilder(sql);
        int offset = (page - 1) * pageSize;
        sqlBuilder.append(" limit ").append(offset).append(",").append(pageSize);
        return sqlBuilder.toString();
    }

    private static String getCountSql(String sql) {
        return "select count(1) from (" + sql + ") as dal_count_table";
    }

    public static class PageTask implements Callable<List<Map<String, Object>>> {
        IDALService dalService;
        String pageSql;
        Object[] params;

        public PageTask(IDALService dalService, String pageSql, Object[] params) {
            this.dalService = dalService;
            this.pageSql = pageSql;
            this.params = params;
        }

        public List<Map<String, Object>> call() throws Exception {
            return this.dalService.findAll(new SqlParam(this.pageSql, this.params));
        }
    }
}
