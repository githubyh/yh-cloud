package com.yh.thread;


import com.yh.page.PageParam;
import com.yh.page.SqlParam;
import com.yh.service.IDALService;
import com.yh.thread.common.MarketingCustom;
import com.yh.thread.common.SplitTimeTask;
import com.yh.util.TsExcelUtils;
import com.yh.util.TsExportCSVTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TsYxwkhdyAndIdCardThread {
	private static Logger logger = LoggerFactory.getLogger(TsYxwkhdyAndIdCardThread.class);
	static String dbName;
	static String collName;
	static String ip;
	static int port;
	static {
		ip = "10.64.4.28";
		port = 27017;
		dbName = "frontendLog";
		collName = "lbs_log";
	}/*
	private static MongoDBCollectionOperation dao = TsMongoUtils.getMongoNoPwd(ip, port, dbName,
			collName);*/

	static long getuserTime = 0L;
	static long getmgtime = 0L;

	static String PATH = "D:/dy/thread/";
	static String SUFFIX = ".csv";
	static IDALService dalService;
/*
	private static MongoLogDao logDao = new MongoLogDao();
	static IDALService tishuDalService = (IDALService) ServiceConfig.ctx.getBean("sjdkdata");
	static IDALService userDalService = (IDALService) ServiceConfig.ctx
			.getBean("DALService.user_userinfo");*/

	private static final ExecutorService executor = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入查询时间范围【2017-01-01 2017-02-01】");

		String line = scanner.nextLine();

		System.out.println("您输入的时间范围：" + line);
		scanner.close();
		String[] arr = line.split("\\_");
		String filename = "twothreeTotal-[" + arr[0].split(" ")[0] + "]";
		String startTime = arr[0].trim();
		String endTime = arr[1].trim();
		TsExcelUtils util = new TsExcelUtils();

		createTileMongDb(filename);

		SplitTimeTask splitTimeTask = new SplitTimeTask();
		List timeSplit = splitTimeTask.getSplitMap(5, startTime, endTime);

		cvsCashCallMongDb(filename, timeSplit);

		System.out.println("***************完成所有***************");
	}

	private static void cvsCashCallTwoThree(String filename, List<Map<String, Object>> timeSplit)
			throws Exception {
		List param = new ArrayList();
		param.add("userId");
		param.add("userName");
		param.add("idCard");
		param.add("mobile");
		param.add("submitTime");

		String sql = " SELECT  userId ,createTime FROM  temp_cash_total_end a  where a.createTime BETWEEN ? and ? order by a.createTime  ";
		getTimeSplitRecord(dalService, timeSplit, sql, param, filename);
	}

	private static void cvsCashCallMongDb(String filename, List<Map<String, Object>> timeSplit)
			throws Exception {
		List param = new ArrayList();
		param.add("id");
		param.add("userId");
		param.add("idCard");
		param.add("mobile");
		param.add("userName");
		param.add("applyId");
		param.add("submitTime");
		param.add("applyMoney");
		param.add("permanentCity");
		param.add("mobileCity");
		param.add("txAddress");
		param.add("hjAddress");
		param.add("dwellAddress1");
		param.add("dwellAddress2");
		param.add("dwellAddress3");
		param.add("dwellAddress4");
		param.add("dwellAddress5");
		param.add("workAddress1");
		param.add("workAddress2");
		param.add("workAddress3");
		param.add("workAddress4");
		param.add("workAddress5");

		String sql = " SELECT id,userId,applyId,createTime FROM temp_cash_total_end a  where  a.createTime BETWEEN ? and ? order by a.createTime  ";
		getTimeSplitRecord(dalService, timeSplit, sql, param, filename);
	}

	public static String getCol(Object obj) {
		return obj + " ";
	}

	public static String getFilePath(String filename) {
		return PATH + filename + SUFFIX;
	}

	public static void createTile(String fileName) {
		List beanList = new ArrayList();

		List resultList = new ArrayList();
		beanList.add("客户编号");
		beanList.add("姓名");
		beanList.add("身份证号");
		beanList.add("手机号");

		beanList.add("提交时间");
		resultList.add(beanList);
		TsExportCSVTask.getInstance().csvWrite(resultList.size() + "", getFilePath(fileName),
				resultList, fileName);
	}

	public static void createTileMongDb(String fileName) {
		List beanList = new ArrayList();

		List resultList = new ArrayList();
		beanList.add("序号");
		beanList.add("客户编号");
		beanList.add("身份证号");
		beanList.add("手机号");
		beanList.add("姓名");
		beanList.add("申请单号");
		beanList.add("提交时间");
		beanList.add("授信额度");
		beanList.add("经常居住城市");
		beanList.add("手机号归属城市");
		beanList.add("通讯地址");
		beanList.add("户籍地址");
		beanList.add("居住地址1");
		beanList.add("居住地址2");
		beanList.add("居住地址3");
		beanList.add("居住地址4");
		beanList.add("居住地址5");
		beanList.add("单位地址1");
		beanList.add("单位地址2");
		beanList.add("单位地址3");
		beanList.add("单位地址4");
		beanList.add("单位地址5");
		resultList.add(beanList);
		TsExportCSVTask.getInstance().csvWrite(resultList.size() + "", getFilePath(fileName),
				resultList, fileName);
	}

	public static void getTimeSplitRecord(IDALService dalService,
                                          List<Map<String, Object>> timeSplitMap, String sql, List<String> paramList,
                                          String fileName) throws Exception {
		List tasks = new ArrayList();
		for (int i = 0; i < timeSplitMap.size(); i++) {
			tasks.add(executor.submit(new MarketingCustom(dalService, new PageParam(
					new SqlParam(sql, new Object[] {
							((Map) timeSplitMap.get(i)).get(SplitTimeTask.BEGIN),
							((Map) timeSplitMap.get(i)).get(SplitTimeTask.END) })))));
		}

		Iterator iterator = tasks.iterator();
		while (iterator.hasNext())
			try {
				logger.info("future.........start.get.." + tasks.toString());
				Future task = (Future) iterator.next();
				List bean = (List) task.get(7200000L, TimeUnit.MILLISECONDS);
				csvWrite(paramList, fileName, bean);
				logger.info("future.........end.");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private static void csvWrite(List<String> paramList, String fileName,
                                 List<Map<String, Object>> bean) {
		List resultList = new ArrayList();

		for (int i = 0; i < bean.size(); i++) {
			Map map = (Map) bean.get(i);
			List beanList = new ArrayList();
			for (int j = 0; j < paramList.size(); j++) {
				beanList.add(map.get(paramList.get(j)).toString());
			}
			resultList.add(beanList);
			if (i % 1000 == 0) {
				TsExportCSVTask.getInstance().csvWrite(bean.size() + "", getFilePath(fileName),
						resultList, fileName);
			}
		}

		if ((resultList != null) && (resultList.size() > 0))
			TsExportCSVTask.getInstance().csvWrite(bean.size() + "", getFilePath(fileName),
					resultList, fileName);
	}

}