/**   
 * @Project: SJDKSer 
 * @Title: wkhtmltopdf.java 
 * @Package com.yanxintec.sjdk 
 * @author dingyl 
 * @date 2016年9月1日 上午11:22:11 
 * @Copyright: 2016 年 研信科技. All rights reserved  
 * @version V1.0   
 */
package com.yh.pdf.two;

import java.io.File;
import java.io.IOException;


public class Wkhtmltopdf {
    // wkhtmltopdf在系统中的路径
    private static final String SEPARATOR = System.getProperties().getProperty("file.separator");
    private static final String TOPDFTOOLWINDOWS = "\\";
    private static final String TOPDFTOOLLINUX = "/";

    /** 
     * html转pdf 
     * @param srcPath html路径，可以是硬盘上的路径，也可以是网络路径 
     * @param destPath pdf保存路径 
     * @return 转换成功返回true 
     * @throws IOException
     * @throws InterruptedException
     */
    public static void convert(String srcPath, String destPath) throws Exception {
        File file = new File(destPath);
        File parent = file.getParentFile();
        // 如果pdf保存路径不存在，则创建路径
        if (!parent.exists()) {
            parent.mkdirs();
        }

        StringBuilder cmd = new StringBuilder();
        if ("/".equals(SEPARATOR)) {
            cmd.append(TOPDFTOOLLINUX);
        } else {
            cmd.append(TOPDFTOOLWINDOWS);
        }
        cmd.append(" ");
        cmd.append(srcPath);
        cmd.append(" ");
        cmd.append(destPath);

        Process proc = Runtime.getRuntime().exec(cmd.toString());
        HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
        HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
        error.start();
        output.start();
        proc.waitFor();
    }

}
