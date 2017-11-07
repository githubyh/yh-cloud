
package com.yh;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.yh.util.TsExcelUtils;
import com.yh.util.TsExportCSVTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mongo {


    // 创建线程池
    private final static ExecutorService executor = Executors.newFixedThreadPool(10);
    final static String topic ="recordAmarsoftInfoLog";
    final static String path = "D:\\ts\\nanj\\";
    public static void main(String[] args) throws Exception {

        String dateBegin = "2017-04-01 00:00:00";
        String dateEnd = "2017-05-01 00:00:00";
//		List<Map<String,Object>> timeSplitResult = new SplitTimeTask().getSplitMap(24,dateBegin,dateEnd);

        TsExcelUtils util = new TsExcelUtils();
        List<List<String>> resultList = new ArrayList ();
        List<Map<String, String>> userList = util.readXlsxRetList("D:\\ts\\nanj\\123.xlsx");
        for (int i = 1; i < userList.size(); i++) {
            String id = userList.get(i).get("0") + "";
            String tradeId = userList.get(i).get("3") + "";
            List<String> result = getData(id, tradeId);
            if(result.size() > 0)
                resultList.add(result);

            if (resultList != null && resultList.size() > 0 && resultList.size() % 500 == 0) {
                List list = new ArrayList(resultList.size());
                list.addAll(resultList);
                TsExportCSVTask.getInstance().csvWrite(list.size() + "", path + topic + ".csv", list, topic);
                resultList.clear();
            }
        }
        if (resultList != null && resultList.size() > 0 ) {
            TsExportCSVTask.getInstance().csvWrite(resultList.size() + "", path + topic + ".csv", resultList, topic);
        }
		/*
        for (int i = 0; i < timeSplitResult.size(); i++) {

			final String begin = timeSplitResult.get(i).get(SplitTimeTask.BEGIN)+"";
			final String end = timeSplitResult.get(i).get(SplitTimeTask.END)+"";

			executor.submit(new Thread(){
				@Override
				public void run(){
					getLog(topic,begin,end);
				}
			}) ;
		}*/

        //获取申请人记录
        System.out.println("********完成所有********");
    }


    public static List<String> getData(  String id, String tradeid) {
        List<String> beanList = new ArrayList ();;
        try {

            Map<String, Object> argMap = new HashMap<String, Object>();
//			Map<String, Object> operateTimeValueMap= new HashMap<String, Object>();
//			operateTimeValueMap.put("$gte", beginTime);
//			operateTimeValueMap.put("$lt", endTime);
//			argMap.put("operateTime",operateTimeValueMap);
            argMap.put("relativeAPPLoanNo", tradeid);
            argMap.put("method", "ChooseOrgMsg");
//			List<SortParam> sortParm = new ArrayList<SortParam>();
//			sortParm.add(new SortParam("operateTime", Order.ASC));

            Map<String, Object> selectFields = new HashMap ();
            selectFields.put("_id", 1);
            selectFields.put("param", 1);

//            List<DBObject> dBObjectList = mongoLogDao.findOneSpecifyResult(argMap, selectFields, dao);
            beanList.add(id);
            beanList.add(tradeid);
            beanList.add("--");




        } catch (Exception e) {
            e.printStackTrace();
        }
        return  beanList;
    }

    public static String getResult(String param){
        JsonParser parse = new JsonParser();
        JsonArray arr = (JsonArray) parse.parse(param);

        if(arr != null){
            for (JsonElement e:arr){
                String as = e.getAsString();
                if(as.indexOf("不符合银行")>0){
                    return as;
                }
            }
        }
        return " ";
    }
}
