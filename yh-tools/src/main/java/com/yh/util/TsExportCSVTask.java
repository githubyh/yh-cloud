package com.yh.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TsExportCSVTask
{
  private static Logger logger = LoggerFactory.getLogger(TsExportCSVTask.class);

  private static TsExportCSVTask exportCSVTask = null;

  Object lock = new Object();

  private static Map<String, ExecutorService> executorMap = new ConcurrentHashMap();

  public static synchronized TsExportCSVTask getInstance()
  {
    if (exportCSVTask == null) {
      exportCSVTask = new TsExportCSVTask();
    }
    return exportCSVTask;
  }

  private ExecutorService getExecutorService(String topic)
  {
    if (StringUtils.isEmpty(topic)) {
      topic = "undefined";
    }

    ExecutorService executor = (ExecutorService)executorMap.get(topic);
    if (executor == null) {
      executor = Executors.newSingleThreadExecutor();
      executorMap.put(topic, executor);
      System.out.println(executorMap.size() + " exportcsvtask........... " + executorMap);
      try {
        TimeUnit.MILLISECONDS.sleep(3000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return executor;
  }

  public void csvWrite(String num, String path, List<List<String>> list) {
    csvWrite(num, path, list, "undefined");
  }

  public void csvWrite(String num, String path, List<List<String>> list, String topic)
  {
    ExecutorService executor = getExecutorService(topic);
    executor.submit(new TsExportCSVThread(num, path, list));
  }

  public static void stopExecutor() {
    Iterator it = executorMap.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry map = (Map.Entry)it.next();
      ExecutorService executor = (ExecutorService)map.getValue();
      executor.shutdownNow();
    }
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread() {
        public void run() {
          for (int j = 0; j < 5; j++)
            TsExportCSVTask.getInstance().getExecutorService("test" + j);
        }
      }
      .start();

      new Thread() {
        public void run() {
          for (int j = 3; j < 8; j++)
            TsExportCSVTask.getInstance().getExecutorService("test" + j);
        }
      }
      .start();
    }
  }
}