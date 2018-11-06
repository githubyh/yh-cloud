
package com.yh.pdf.two;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CreatePdf {
    private static Logger logger = LoggerFactory.getLogger(CreatePdf.class);
    // 下载根路径
    private static String downPath ="/down";
    
    private CreatePdf(){}

    /**
     * 生成pdf文件
     *
     */
    public static String createPdf(String inputFile, String createContName, String fileType,
                                   String applyId, String userId, String tradeId) throws Exception {
        String outputFilePath = "";
        String fdsPath = "";
        createContName = createContName.substring(0, createContName.lastIndexOf('.'));
        outputFilePath = downPath + File.separator + createContName + ".pdf";
        Wkhtmltopdf.convert(inputFile, outputFilePath);

        return fdsPath;
    }

    /**
     * 将用户的身份证正反面图片合成PDF文件
     */
    public static String createPdfImage(String frontPath, String backPath) {
        FileOutputStream pdfFile = null;
        Document document = null;
        String fileName = UUID.randomUUID().toString();
        String pdfPath = downPath + "/" + fileName + ".pdf";
        try {
            // 将要生成的 pdf 文件的路径输出流
            pdfFile = new FileOutputStream(new File(pdfPath));
            document = PdfUtils.createDocument(0, 0, 0, 0);
            Image idCardFace = PdfUtils.createImage(frontPath, true);
            idCardFace.scaleAbsolute(300, 150);// 自定义大小
            Image idCardBack = PdfUtils.createImage(backPath, true);
            idCardBack.scaleAbsolute(300, 150);// 自定义大小

            Font font = PdfUtils.createFont(0, false);
            Paragraph blank = PdfUtils.createParagraph(" ", font, 0, false);

            // 用 Document 对象、File 对象获得 PdfWriter 输出流对象
            PdfWriter writer = PdfWriter.getInstance(document, pdfFile);
            writer.setStrictImageSequence(true);
            document.open(); // 打开 Document 文档
            // 添加图片文件
            document.add(blank);
            document.add(idCardFace);
            document.add(blank);
            document.add(idCardBack);
        } catch (Exception e) {
            logger.error("将身份证正反面文件生成pdf出现异常！", e);
        } finally {
            if ( document!=null) {
                document.close();
            }
            if ( pdfFile!=null) {
                try {
                    pdfFile.close();
                } catch (IOException e) {
                    logger.error("将身份证正反面文件生成pdf出现异常(关闭文件流)！", e);
                }
            }
        }
        // 将pdf文件转化为图片
        String imagePath = downPath + "/" + fileName + ".jpg";
        pdfToImgages(pdfPath, imagePath);
        return imagePath;
    }

    /**
     * 身份证正反面pdf转图片
     */
    private static void pdfToImgages(String pdfPath, String imagePath) {
        PDDocument doc = null;
        FileOutputStream out = null;
        ImageOutputStream outImage = null;
        try {
            doc = PDDocument.load(pdfPath);
            List<?> pages = doc.getDocumentCatalog().getAllPages();
            for (int i = 0; i < pages.size(); i++) {
                PDPage page = (PDPage) pages.get(i);
                BufferedImage image = page.convertToImage();
                Iterator<ImageWriter> iter = ImageIO.getImageWritersBySuffix("jpg");
                ImageWriter writer =  iter.next();
                out = new FileOutputStream(new File(imagePath));
                outImage = ImageIO.createImageOutputStream(out);
                writer.setOutput(outImage);
                writer.write(new IIOImage(image, null, null));
            }
        } catch (Exception e) {
            logger.error("将身份证正反面pdf转图片出现异常！", e);
        } finally {
            if ( doc!=null) {
                try {
                    doc.close();
                } catch (IOException e) {
                    logger.error("将身份证正反面pdf转图片出现异常！", e);
                }
            }
            if ( out!=null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("将身份证正反面pdf转图片出现异常！", e);
                }
            }
            if ( outImage !=null) {
                try {
                    outImage.close();
                } catch (IOException e) {
                    logger.error("将身份证正反面pdf转图片出现异常！", e);
                }
            }
        }
    }
}
