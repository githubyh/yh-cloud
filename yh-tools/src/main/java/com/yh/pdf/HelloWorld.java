package com.yh.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
public class HelloWorld {
 public static void main(String[] args) throws IOException {
  //创建一个文档对象
  Document doc = new Document();
  try {
   // 定义输出位置并把文档对象装入输出对象中
   PdfWriter.getInstance(doc, new FileOutputStream("d:/hello.pdf"));
   // 打开文档对象
   doc.open();
   
   // 设置中文字体

//   BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
   BaseFont bfChinese = BaseFont.createFont();
   Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
   // 加入文字“HelloWorld ------ 中国北京,我的2008 .”
   
   String str = "HelloWorld ------ 中国北京, 我的2008 .";
   
   Paragraph tt = new Paragraph(str, FontChinese);
   doc.add(tt);
   // 加入图片Deepinpl.jpg

   Image jpg = null;

    jpg = Image.getInstance("d:/集体签名.jpg");


   jpg.setAlignment(Image.ALIGN_CENTER);

   doc.add( jpg);
   // 关闭文档对象，释放资源
   
   doc.close();
   
   

  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (DocumentException e) {
   e.printStackTrace();
  }
  
  System.out.println("OK");
 }
}