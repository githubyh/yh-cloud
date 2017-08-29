package com.yh.thread.common;

import com.yh.page.SqlParam;
import com.yh.service.IDALService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplitTimeTask
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

  public List<Map<String, Object>> getSplitMap(int timeInterval, String dateFrom, String dateTo) {
    List result = new ArrayList();

    timeInterval = (timeInterval > 0) && (timeInterval <= 24) ? timeInterval : 3;

    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long from = 0L;
    long to = 0L;
    int threadNums = 0;
    long timeIntervalMill = 3600000 * timeInterval;
    long remainder = 0L;
    try {
      from = df2.parse(dateFrom).getTime();
      to = df2.parse(dateTo).getTime();
      threadNums = (int)((to - from) / timeIntervalMill);
      remainder = (to - from) % timeIntervalMill;
    } catch (ParseException e) {
      logger.error(e.getMessage(), e);
    }

    String datePartFrom = "";
    String datePartTo = "";

    for (int i = 1; i <= threadNums; i++) {
      datePartFrom = df2.format(new Date(from));
      to = from + 3600000 * timeInterval;
      datePartTo = df2.format(new Date(to));
      Map beanMap = new HashMap();
      beanMap.put(BEGIN, datePartFrom);
      beanMap.put(END, datePartTo);
      System.out.println(datePartFrom + "," + datePartTo);
      result.add(beanMap);
      from = to;
    }

    if (remainder > 0L) {
      datePartTo = df2.format(new Date(from));
      Map beanMap = new HashMap();
      beanMap.put(BEGIN, datePartTo);
      beanMap.put(END, dateTo);
      result.add(beanMap);
    }
    return result;
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