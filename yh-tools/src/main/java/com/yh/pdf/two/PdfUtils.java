package com.yh.pdf.two;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * 生成PDF文件工具类
 * @ClassName PdfUtils  
 * @Description 生成PDF文件工具类 
 * @author daidg 
 * @date 2015年7月6日  
 */
public class PdfUtils {
    // 编译根路径
    private static String basePath = "";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PdfUtils.class);
    
    private PdfUtils(){}

    /**
    * 创建生成PDF文件所需的基础对象Document(默认按A4纸生成)
    * @param marginLeft 左外边距
    * @param marginRight 右外边距
    * @param marginTop 顶部外边距
    * @param marginBottom 底部右外边距
    * @return Document PDF文件基础类
    */
    public static Document createDocument(float marginLeft, float marginRight, float marginTop,
                                          float marginBottom) {
        // 作为报表的PDF文件，一定要适合打印机的输出打印
        Rectangle rectPageSize = new Rectangle(PageSize.A4.rotate());// 定义A4页面大小
        return new Document(rectPageSize, marginLeft, marginRight, marginTop,
                marginBottom);// 其余4个参数，设置了页面的4个边距
    }

    /**
    * 设置PDF文件字体
     * @param size 字体大小,必须大于零，否则取默认值
    * @param isBold 是否需要加粗
    * @return Font 生成PDF文件需要的字体
    * @throws IOException
    * @throws DocumentException
    */
    public static Font createFont(float size, boolean isBold) throws DocumentException, IOException {
        // 方法二：使用iTextAsian.jar中的字体
        String fontPath = basePath + "/font/simsun.ttc,1";
        BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(baseFont);
        if (size > 0) {
            font.setSize(size);
        }
        if (isBold) {
            font.setStyle(Font.BOLD);
        }
        return font;
    }

    /**
     * 设置PDF文件段落内容
     * @param content 需要生成的内容
     * @param font 字体
     * @param firstLineIndent 首行缩进数
     * @param isCenter 是否需要居中显示
     * @return Paragraph 生成PDF文件的段落
     */
    public static Paragraph createParagraph(String content, Font font, int firstLineIndent,
                                            boolean isCenter) {
        Paragraph paragraph = new Paragraph(content, font);
        if ( firstLineIndent>0) {
            paragraph.setFirstLineIndent(firstLineIndent);
        }
        if (isCenter) {
            paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        }
        return paragraph;
    }

    /**
     * 设置在PDF文件中生成图片
     * @param filePath 图片路径
     * @param isCenter 图片是否需要居中
     * @return Image 图片
     * @throws IOException
     * @throws MalformedURLException
     * @throws BadElementException
     */
    public static Image createImage(String filePath, boolean isCenter) throws BadElementException,
            MalformedURLException, IOException {
        Image image = Image.getInstance(filePath);
        if (isCenter) {
            image.setAlignment(Image.ALIGN_CENTER);
        }
        return image;
    }

}
