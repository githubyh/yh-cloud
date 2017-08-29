
package com.yh.thread;

import com.yh.page.DALByPage;
import com.yh.page.PageParam;
import com.yh.page.SqlParam;
import com.yh.service.IDALService;
import com.yh.thread.common.BaseClass;
import com.yh.thread.common.ExportTask;
import com.yh.util.ExcConstant;
import com.yh.util.TsExportCSVTask;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tsjianhang extends BaseClass {

	private static IDALService dalService  ;
	
	private static final ExecutorService executor = Executors.newFixedThreadPool(12);
	
	public static Map<String, Object> getCount(String topic) {
		String sql = " SELECT count(1) as count FROM "+topic+" a ";
		return dalService.find(new SqlParam(sql, null));
	}
	
	public List<Map<String, Object>> getTradeInfo(long startId, long endId, String topic) {
		String sql = " SELECT *  FROM "+topic+" a where a.id>=? and a.id<? ";
		Object[] param = new Object[] { startId,endId};
		List<Map<String, Object>> list = DALByPage.searchByPage(dalService, new PageParam(new SqlParam(sql, param), 2000));
		return list;
	}
	
	@Override
	public void cal(long pageNo, long pageSize,String topic) {
		long startId = pageNo*pageSize;
		long endId =startId+pageSize;
		List<Map<String, Object>> list = getTradeInfo(startId,endId,topic);
		List<List<String>> resultList =new ArrayList ();
		
		try {
		Map<String, Object> map =null;
		int j=0;
		for (int i = 0; i < list.size(); i++) {
			++j;
			
			map = list.get(i);
			String id = map.get("id")+"";
			String tradeId =map.get("tradeId")+"";
			List<String> beanList = getInfo(map);
			resultList.add(beanList);
			if (j%500==0) {
				List wlist =  new ArrayList<List<String>>() ;
				wlist.addAll(resultList);
				TsExportCSVTask.getInstance().csvWrite(wlist.size()+"","D:\\jh\\"+topic+".csv", wlist,topic);
				resultList.clear();
			}
		}
		System.out.println("完成"+topic+","+pageNo+","+list.size());
		
		if (resultList != null&&resultList.size()>0) {
			List rlist =new ArrayList<List<String>>() ;
			rlist.addAll(resultList);
			TsExportCSVTask.getInstance().csvWrite(rlist.size()+"","D:\\jh\\"+topic+".csv", rlist,topic);
			resultList.clear();
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void test(long pageNo, long pageSize) {
		System.out.println(pageNo+","+pageSize);
	}
	
	
	public static void splitTask(String topic) throws InterruptedException {
		Map<String,Object> map =getCount(topic);
		
		long totalCount = Long.valueOf(map.get("count").toString());
		System.out.println(totalCount);
		int pageSize = 1000;
		int num=(int)totalCount/pageSize;
		long remainder=totalCount%pageSize;
		if(remainder>0){
			++num;
		}
//		int num=1;
		CountDownLatch c = new CountDownLatch(num);
		List<List<String>> titleList =new ArrayList ();
		List<String> titleBean = new ArrayList ();
		titleBean.add("序号");
		titleBean.add("tradeId");

		titleList.add(titleBean);
		TsExportCSVTask.getInstance().csvWrite(titleList.size()+"","D:\\jh\\"+topic+".csv", titleList,topic);
		
		//查询用户
		for (int i = 0; i < num; i++) {
			executor.submit(new ExportTask(c,i,pageSize,new Tsjianhang(),topic));
		}
		c.await();
		System.out.println("******完成"+topic+"******");
	}
	
	public static void main(String args[]) {
		try {
			String topic = "temp_jhdh";
			splitTask(topic);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	
	
	public static List<String> getInfo(Map<String, Object> map){
		try {
			List<String> beanList = new ArrayList ();
			

			String id = map.get("id").toString();
			String tradeId = map.get("tradeId").toString();
			String applyId = map.get("applyId").toString();

			

				
				applyId = applyId == null ? ExcConstant.EMPTY : applyId;

				beanList.add(id);
				beanList.add(tradeId);

				return beanList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
	


	@Override
	public void cal(long pageNo, long pageSize) {
	}

}
