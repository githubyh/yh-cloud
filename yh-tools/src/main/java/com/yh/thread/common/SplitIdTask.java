package com.yh.thread.common;


import com.yh.page.SqlParam;
import com.yh.service.IDALService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplitIdTask
{
  public static String BEGIN = "begin";
  public static String END = "end";

  private static Logger logger = LoggerFactory.getLogger( SplitTimeTask.class);

  private static final ExecutorService executor = Executors.newFixedThreadPool(10);

  public List<Map<String, Object>> converSplitIdMap(IDALService dalService, String sql, List<Map<String, Object>> list, String columnName) {
    List result = new ArrayList();
    if ((list == null) || (list.size() == 0)) {
      return null;
    }
    Long begin = getAutoIndex(dalService, sql, ((Map)list.get(0)).get(BEGIN) + "", columnName);

    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
      Long end = getAutoIndex(dalService, sql, ((Map)list.get(i)).get(END) + "", columnName);
      Map map = new HashMap();
      map.put(BEGIN, begin);
      map.put(END, end);
      System.out.println(begin + "," + end);
      result.add(map);
      begin = end;
    }
    return result;
  }

  public Long getAutoIndex(IDALService dalService, String sql, String beginTime, String columnName) {
    List beforeList = dalService.findAll(new SqlParam(sql, new Object[] { beginTime }));

    String autoIndexStr = ((Map)beforeList.get(0)).get(columnName) + "";
    if (StringUtils.isBlank(autoIndexStr))
      return null;
    Long autoIndex = Long.valueOf(Long.parseLong(autoIndexStr));
    return autoIndex;
  }

  public List<Map<String, Object>> getSplitMap(int timeInterval, int startId, int endId) {
    List result = new ArrayList();

    timeInterval = (timeInterval == 0) ? 1000 : timeInterval;
  
    int remainder = 0 ;
    remainder = (endId - startId) / timeInterval;
    
    if(endId % timeInterval > 0 ) remainder++; 
    if(remainder==0 && endId>startId)remainder++;
    int begin = 0,end=0 ;
    for (int i = 0; i <= remainder; i++) {
    	if(i == 0){
    		begin = startId ; 
    	}
    	else
    	{
    		begin = end;
    	}
    	end = startId + (i+1) * timeInterval + 1;
    	if(end > endId)
    		end = endId;
//    	if(i  == remainder){
//			end = endId;
//		}
    	 Map beanMap = new HashMap();
         beanMap.put(BEGIN, begin);
         beanMap.put(END, end);
         result.add(beanMap);
         if(end >=endId) break;
    }
    
    return result;
  }
  

  public static void main(String[] args)
    throws Exception
  {
	  SplitIdTask splitIdTask = new SplitIdTask();
		List timeSplit = splitIdTask.getSplitMap(1000, 0, 1375057);
		for (Object object : timeSplit) {
			Map map = (Map)object ;
			System.out.println(map.get(BEGIN) + " == " + map.get(END));
		}
  }

  /*public List<Map<String, Object>> split(IDALService dalService, String sql, int timeInterval, String dateFrom, String dateTo)
  {
    List timeSplitMap = getSplitMap(timeInterval, dateFrom, dateTo);
    List tasks = new ArrayList();
    for (int i = 0; i < timeSplitMap.size(); i++) {
      tasks.add(executor.submit(new SingleThread(dalService, new PageParam(new SqlParam(sql, new Object[] { ((Map)timeSplitMap.get(i)).get(BEGIN), ((Map)timeSplitMap.get(i)).get(END) })))));
    }

    List result = new ArrayList();
    Iterator iterator = tasks.iterator();
    while (iterator.hasNext()) {
      try {
        Future task = (Future)iterator.next();
        Collection bean = (Collection)task.get(360000L, TimeUnit.MILLISECONDS);
        if (bean != null)
          result.addAll(bean);
      }
      catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
    return result;
  }*/
}