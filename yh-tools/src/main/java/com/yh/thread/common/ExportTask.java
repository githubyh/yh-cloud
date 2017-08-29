
package com.yh.thread.common;

import org.apache.commons.lang.StringUtils;

import java.util.concurrent.CountDownLatch;


public class ExportTask extends Thread {
	
	long pageNo;
	long pageSize;
	BaseClass cls;
	CountDownLatch c;
	String topic;
	
	public ExportTask(CountDownLatch c, long pageNo, long pageSize, BaseClass cls) {
		this.c=c;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.cls=cls;
	}
	
	public ExportTask(CountDownLatch c, long pageNo, long pageSize, BaseClass cls, String topic) {
		this.c=c;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.cls=cls;
		this.topic = topic;
	}

	public void run() {
		if (StringUtils.isNotEmpty(topic)) {
			cls.cal(pageNo,pageSize,topic);
		} else {
			cls.cal(pageNo,pageSize);
		}
//		cls.test(pageNo, pageSize);
		System.err.println("******完成+"+pageNo+"+******");
		c.countDown();
	}
}
