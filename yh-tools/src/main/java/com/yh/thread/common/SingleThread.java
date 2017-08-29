package com.yh.thread.common;


import com.yh.page.DALByPage;
import com.yh.page.PageParam;
import com.yh.service.IDALService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public abstract  class SingleThread
  implements Callable
{
  private static Logger logger = LoggerFactory.getLogger(SingleThread.class);
  protected IDALService dalService;
  private PageParam pageParam;
  private List<Map<String, Object>> list;

  public List<Map<String, Object>> getList()
  {
    return this.list;
  }

  public SingleThread(IDALService dalService, PageParam pageParam) {
    this.dalService = dalService;
    this.pageParam = pageParam;
  }
  public String param ;

  private void before() {
	 
    
    for (Object o : this.pageParam.getSqlParam().getParams())
    {
      param = param + "_" + o.toString();
    }
    logger.info(param);
    this.list = DALByPage.searchByPage(dalService, pageParam);
  }

  public Object call() throws Exception
  {
	  long start = System.currentTimeMillis();
    before();
//    logger.info("before 耗时："+(System.currentTimeMillis()-start));
    return doSometing();
  }

  
  public abstract List<Map<String, Object>> doSometing();
}