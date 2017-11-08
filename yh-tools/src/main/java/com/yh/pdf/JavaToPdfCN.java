package com.yh.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
  
/**

 * iText默认是不支持中文的，因此需要添加对应的中文字体,比如黑体simhei.ttf
 */
public class JavaToPdfCN {
  
    private static final String DEST = JavaToPdf.PATH +  "/target/HelloWorld_CN.pdf";
    private static final String FONT = "pdf/simhei.ttf";
  
  
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        Font f1 = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        document.add(new Paragraph("hello world,我是鲁家宁", f1));
        document.close();
        writer.close();
    }
}