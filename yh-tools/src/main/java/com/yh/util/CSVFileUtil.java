
package com.yh.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CSVFileUtil {
	private static Logger logger = LoggerFactory.getLogger(CSVFileUtil.class);
	public static void appendCSVFile(String fileName, List<List<String>> lineDataList){
        final String NEW_LINE = "\n";
        try {     
        	File file =new File(fileName);
        	//如果不存在,创建一个新文件
            if(!file.exists()){
             file.createNewFile();
            }
            StringBuilder csvStr = new StringBuilder();
            //数据行
            for(int i=0; i<lineDataList.size(); i++){
            	List<String> lineData  = lineDataList.get(i);
            	String csvData="";
//            	if(lineData != null){
	            	for(String metaData:lineData){
	            		if(metaData.indexOf(',')>0){
	            			metaData=metaData.replace(',', '.');            			
	            		}
	            		if(!csvData.endsWith(",")&&!csvData.equals("")){
	            			csvData+=",";            			
	            		}
	            		csvData+=metaData;
	            	}
	            	csvStr.append(csvData).append(NEW_LINE);
	                lineData.clear();
//            	}else{
//            		List<String> s = lineDataList.get(i-1);
//            		System.out.println(s.get(0) + " " + s.get(1));
//            	}
                
            }           
            //写文件
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName),true), "GB2312"));
            writer.write(csvStr.toString());
            writer.flush();
            writer.close();

            lineDataList.clear();            
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("CSV 文件末尾添加数据异常：" + e);
            e.printStackTrace();
        }        
    }
	/** 
	 * @Title: main 
	 * @Description:  (这里用一句话描述这个方法的作用)
	 * @param args 参数说明
	 * @return void    返回类型
	 */
	public static void main(String[] args) {
		List<String> stringList1=new ArrayList<String>();
		stringList1.add("hello");
		stringList1.add("world");
		stringList1.add("american");
		List<String> stringList2=new ArrayList<String>();
		stringList2.add("japan");
		stringList2.add("korean");
		stringList2.add("american");
		List<List<String>> StringListList=new ArrayList<List<String>>();
		StringListList.add(stringList1);
		StringListList.add(stringList2);
		CSVFileUtil.appendCSVFile("D:\\333.csv",StringListList);

	}

}
