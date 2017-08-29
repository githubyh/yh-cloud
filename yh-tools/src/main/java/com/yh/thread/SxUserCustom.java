package com.yh.thread;


import com.yh.page.PageParam;
import com.yh.page.SqlParam;
import com.yh.service.IDALService;
import com.yh.thread.common.SingleThread;
import com.yh.util.ExcConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SxUserCustom extends SingleThread {
	private static Logger logger = LoggerFactory.getLogger(SxUserCustom.class);
	private String fileName;
	private String topic = "z_temp_sxuser";
	static IDALService dalService;
	static String sb = "";

	 

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public SxUserCustom(IDALService dalService, PageParam pageParam) {
		super(dalService, pageParam);
		this.dalService = dalService;
		this.fileName = this.fileName;
		 
	} 
 

	public static List<String> getData(Map<String, Object> map) {
		String id = map.get("id") + "";
		String userId = map.get("userid") + "";

		Map<String, Object> sxinfoMap = getUserInfo(userId);
		if(sxinfoMap == null){
//			System.out.println(userId);
			return null;}
		List beanList = new ArrayList();

		String applyId = sxinfoMap.get("applyId") + "";
		String idCard = sxinfoMap.get("idCard") + "";

		id  = StringUtils.isBlank(id) ? ExcConstant.EMPTY : id ;
		applyId = StringUtils.isBlank(applyId ) ? ExcConstant.EMPTY : applyId ;

		beanList.add(id);
		beanList.add(userId);
		beanList.add(applyId);

		return beanList;
	}

	public static Map<String, Object> getUserInfo(String userId)
	{

		return null;
	}
	public static  String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	public static int addInt(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
         
		if (StringUtils.isBlank(str)) {
			return 0;
		}
		int cnt = 0 ;
		int end1 = 0;
		int end2 = 0;
		int end3 = 0;
		if (str.length() >= 1){
			try{
//			end1 = Integer.valueOf(str.substring(str.length() - 1));
				if(pattern.matcher(str.substring(str.length() - 1)).matches()){
					cnt++;
				}
			}catch(Throwable t){}
		}
		if (str.length() >= 2){
			try{
//			end2 = Integer.valueOf(str.substring(str.length() - 2, str.length() - 1));
				if(pattern.matcher(str.substring(str.length() - 2, str.length() - 1)).matches()){
					cnt++;
				}
			}catch(Throwable t){}
		}
		if (str.length() >= 3){
			try{
//			end3 = Integer.valueOf(str.substring(str.length() - 3, str.length() - 2));
				if(pattern.matcher(str.substring(str.length() - 3, str.length() - 2)).matches()){
					cnt++;
				}
			}catch(Throwable t){}
		}
		return cnt;
	}
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
  }
	public List<Map<String, Object>> doSometing() {
		long start = System.currentTimeMillis() ;
		List<Map<String, Object>> list = getList();

		long start2 = System.currentTimeMillis() ;
//		 logger.info(param + " getList查询耗时 ============="+(start2-start));
		List<List<String>> resultList = new ArrayList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				List<String> ls =getData(list.get(i));
				if(ls != null)
				resultList.add(ls);
				if(i % 500 == 0)
				logger.info(i+"");
			}
		}
		list.clear();
		 
//		 logger.info(param + " getData =============完成耗时:"+(System.currentTimeMillis()-start2));
		 List<Map<String, Object>> rl = new ArrayList();
		 Map map = new HashMap();
		 map.put("result", resultList);
		 rl.add(map);
		return rl;
	}
}