package com.yh.thread;


import com.yh.page.PageParam;
import com.yh.page.SqlParam;
import com.yh.service.IDALService;
import com.yh.thread.common.SplitIdTask;
import com.yh.thread.common.SplitTimeTask;
import com.yh.util.TsExportCSVTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SxUserThread {
    private static Logger logger = LoggerFactory.getLogger(SxUserThread.class);


    //	private static MongoDBCollectionOperation dao = MongoUtils.getMongoNoPwd(ip, port, dbName,
//			collName);
    Random random = new Random();
    static long getuserTime = 0L;
    static long getmgtime = 0L;


    static String PATH = "D:/ts/sxuser/";
    static String SUFFIX = ".csv";
    static Map<String, String> userMap = new ConcurrentHashMap();

//	private static MongoLogDao logDao = new MongoLogDao();


    private IDALService tishuDalService  ;
    /**
     * getSplitMap(200, 1, 100000);
     * thread num
     * 20 69540
     * 25 65538
     * 35 59971
     * 45 55606
     * 55 53225
     */
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        String filename = "sx_userinfo";
        long istart = System.currentTimeMillis();
        SplitIdTask splitIdTask = new SplitIdTask();
        List timeSplit = splitIdTask.getSplitMap(1000, 465001, 465300);

        createTile(filename);

        new SxUserThread().cvsCashCall(filename, timeSplit);
        System.out.println("***************完成所有***************" + (System.currentTimeMillis() - istart));

        System.out.println("userMap:" + (userMap.size()));

        for (Map.Entry<String, String> e : userMap.entrySet()) {
            System.out.println(e.getKey());
        }
        executor.shutdownNow();
        TsExportCSVTask.stopExecutor();
    }

    private void cvsCashCall(String filename, List<Map<String, Object>> timeSplit)
            throws Exception {
        String sql = " SELECT * from z_temp_sxUser   where id >=? and id < ? order by id  ";
        getTimeSplitRecord(tishuDalService, timeSplit, sql, null, filename);
    }

    public static String getCol(Object obj) {
        return obj + " ";
    }

    public static String getFilePath(String filename) {
        return PATH + filename + SUFFIX;
    }

    public static void createTile(String fileName) {
        List resultList = new ArrayList();
        List beanList = new ArrayList();
        beanList.add("序号");
        beanList.add("客户编号");
        beanList.add("申请编号");

        resultList.add(beanList);
        TsExportCSVTask.getInstance().csvWrite(resultList.size() + "", getFilePath(fileName),
                resultList, fileName);
    }

    public void getTimeSplitRecord(IDALService dalService,
                                   List<Map<String, Object>> timeSplitMap, String sql, List<String> paramList,
                                   String fileName) throws Exception {
        List tasks = new ArrayList();
        for (int i = 0; i < timeSplitMap.size(); i++) {

            tasks.add(executor.submit(new SxUserCustom(dalService, new PageParam(new SqlParam(sql,
                    new Object[]{((Map) timeSplitMap.get(i)).get(SplitTimeTask.BEGIN),
                            ((Map) timeSplitMap.get(i)).get(SplitTimeTask.END)})))));

        }
        ThreadPoolExecutor tpe = (ThreadPoolExecutor) executor;

        Iterator iterator = tasks.iterator();
        while (iterator.hasNext()) {
            try {
                logger.info("=======任务数：" + tpe.getTaskCount() + "=======  活动线程数：" + tpe.getActiveCount() + "=======  CompletedTaskCount：" + tpe.getCompletedTaskCount() + "=======  LargestPoolSize：" + tpe.getLargestPoolSize());
//				long start = System.currentTimeMillis() ;
//				logger.info("future.........start.get.." );
                Future task = (Future) iterator.next();
                List<Map<String, Object>> bean = (List<Map<String, Object>>) task.get(2660000, TimeUnit.MILLISECONDS);
//				logger.info("future.........end."+(System.currentTimeMillis()-start));
                csvWrite(null, fileName, bean);
                bean.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    private void csvWrite(List<String> paramList, String fileName,
                          List<Map<String, Object>> bean) {

//		long start = System.currentTimeMillis() ;
        if (bean != null && bean.size() > 0) {
            List<List<String>> resultList = (List<List<String>>) bean.get(0).get("result");
            List wr = new ArrayList(resultList.size());
            wr.addAll(resultList);
            TsExportCSVTask.getInstance().csvWrite(wr.size() + "", getFilePath(fileName),
                    wr, fileName);
//			resultList.clear();
        }

//		logger.info("====================over:"+(System.currentTimeMillis()-start));
    }
}