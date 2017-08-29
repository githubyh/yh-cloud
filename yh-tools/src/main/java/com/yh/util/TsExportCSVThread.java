package com.yh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

class TsExportCSVThread extends Thread
{
  private static Logger logger = LoggerFactory.getLogger(TsExportCSVTask.class);
  String num;
  String path;
  private List<List<String>> list;

  public TsExportCSVThread(String num, String path, List<List<String>> list)
  {
    this.num = num;
    this.path = path;
    this.list = list;
  }

  public void run() {
    logger.info("正在写入第" + this.num + "份"+path);
  
    CSVFileUtil.appendCSVFile(this.path, list);
    logger.info("第" + this.num + "份完成");
  }
}