package com.yh.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
  

public class JavaToPdf {

    public  final static String PATH  = System.getProperty("user.dir").replaceAll("\\\\","/") + "/yh-tools/";
    public static final String TEMPLATE_PATH  = JavaToPdf.PATH + "target/classes/pdf/";
    private static final String DEST = PATH + "/target/HelloWorld.pdf";
  
  
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        System.out.println();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        document.add(new Paragraph("hello world"));
        document.close();
        writer.close();

    }
}