package com.yh.util;


import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作中经常需要对Excel进行读写操作， java操作excel文件比较流行的是apache poi包，
 * excel分为xls（2003）和xlsx（2007）两种格式， 操作这两种格式的excel需要不同的poi包。
 *
  
 */

public class TsExcelUtils {

  public String getCellValueString(HSSFCell cell){
    if (null == cell)
      return "";
    switch (cell.getCellType()) {
      case HSSFCell.CELL_TYPE_NUMERIC: // 数字
        return cell.getNumericCellValue()+"";
      case HSSFCell.CELL_TYPE_STRING: // 字符串
        return cell.getStringCellValue()+"";
      case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
        return cell.getBooleanCellValue()+"";
      case HSSFCell.CELL_TYPE_FORMULA: // 公式
        return cell.getCellFormula()+"";
      case HSSFCell.CELL_TYPE_BLANK: // 空值
        return "";
      case HSSFCell.CELL_TYPE_ERROR: // 故障
        return "";
      default:
        return "";
    }
  }

  /**
   *
   * @Title: readXls
   * @Description: 读取xls
   * @param path
   * @throws FileNotFoundException
   * @throws IOException
   *             参数说明
   * @return void 返回类型
   */
  public void readXlsAll(String path) throws FileNotFoundException, IOException {
    File file = new File(path);
    POIFSFileSystem poifsFileSystem = new POIFSFileSystem(
            new FileInputStream(file));
    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
    for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
      HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);

      if (hssfSheet == null) {
        continue;
      }
      String sheetName = hssfSheet.getSheetName().trim();

      int rowstart = hssfSheet.getFirstRowNum();
      int rowEnd = hssfSheet.getLastRowNum();

      List<List<String>> resultList =new ArrayList();
      List<String> beanList=null;


      loop1: for (int i = rowstart+1; i <= rowEnd; i++) {

        beanList =new ArrayList ();

        HSSFRow row = hssfSheet.getRow(i);
        if (null == row)
          continue;
        int cellStart = row.getFirstCellNum();
        int cellEnd = row.getLastCellNum();
        beanList.add(numSheet+"");
        beanList.add(sheetName);
        for (int k = cellStart; k <= cellEnd; k++) {
          HSSFCell cell = row.getCell(k);
          String col = getCellValueString(cell);
          if ((StringUtils.isEmpty(col) && k == cellStart)||"返回目录".equals(col.trim())) {
            break loop1;
          }
          beanList.add(StringUtils.isEmpty(col.trim())?"unknow":col.trim());

        }
        if (beanList!=null&&beanList.size()>0) {
          resultList.add(beanList);
        }
      }
      System.out.println("完成"+sheetName+"表,"+numSheet);
      if (resultList!=null&&resultList.size()>0) {
        System.out.println(resultList);
         TsExportCSVTask.getInstance().csvWrite(resultList.size()+"","D:\\zx.csv", resultList,"zx");
      }

    }
    System.out.println("********完成所有****");
  }


  public static void main(String[] args) throws IOException {
    // readXlsx("D://1.xlsx");
//    new  TsExcelUtils().readXlsAll("D://1.xls") ;
     writeXls("D://3.xls");
    // writeXlsx("D://4.xlsx");
    // appendXlsx("D://4.xlsx");

    // List<Map<String, String>> list = new ArrayList<>();
    // Map<String, String> map = new HashMap<String, String>();
    // map.put("0", "1");
    // map.put("1", "1");
    // map.put("2", "1");
    // map.put("3", "1");
    // list.add(map);
    // map = new HashMap<String, String>();
    // map.put("0", "2");
    // map.put("1", "2");
    // map.put("2", "2");
    // map.put("3", "2");
    // list.add(map);
    // appendXlsx("D://5.xlsx", list);

//		readXlsxRetList("D://test.xlsx");
  }

  public void appendXlsx(String path, List<Map<String, Object>> list,
                         int sheetNo)  {

    try {
      if (list == null || list.size() == 0) {
        System.out.println("數據為空");
        return;
      }

      File file = new File(path);
      // 如果不存在,创建一个新文件
      if (!file.exists()) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.createSheet("data0");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
      }

      InputStream is = new FileInputStream(path);
      XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
      XSSFSheet sheet = xssfWorkbook.getSheetAt(sheetNo); // 获取到工作表，因为一个excel可能有多个工作表
      XSSFRow row = sheet.getRow(0); // 获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
      if (row != null) {
        System.out.println(sheet.getLastRowNum() + " "
                + row.getLastCellNum()); // 分别得到最后一行的行号，和一条记录的最后一个单元格
      }

      FileOutputStream out = new FileOutputStream(path); // 向d://test.xls中写数据
      Map<String, Object> map = null;

      for (int i = 0; i < list.size(); i++) {
        map = list.get(i);
        row = sheet.createRow((short) (sheet.getLastRowNum() + 1)); // 在现有行号后追加数据

        // row.createCell(1).setCellValue(map.get("1")); //
        // 设置第一个（从0开始）单元格的数据
        // row.createCell(0).setCellValue(map.get("0")); //
        // 设置第二个（从0开始）单元格的数据
        for (int j = 0; j < map.size(); j++) {
          row.createCell(j).setCellValue(map.get(j + "")+""); // 设置第一个（从0开始）单元格的数据
        }
      }

      out.flush();
      xssfWorkbook.write(out);
      out.close();
      // System.out.println(row.getPhysicalNumberOfCells() + " " +
      // row.getLastCellNum());
      System.out.println("完成，共添加" + list.size() + "行");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  public void appendXlsx(String path) throws IOException {

    File file = new File(path);
    // 如果不存在,创建一个新文件
    if (!file.exists()) {
      XSSFWorkbook workbook = new XSSFWorkbook();
      workbook.createSheet("data");
      FileOutputStream fileOutputStream = new FileOutputStream(file);
      workbook.write(fileOutputStream);
      fileOutputStream.close();
    }

    InputStream is = new FileInputStream(path);
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    XSSFSheet sheet = xssfWorkbook.getSheetAt(0); // 获取到工作表，因为一个excel可能有多个工作表
    XSSFRow row = sheet.getRow(0); // 获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
    if (row != null) {
      System.out.println(sheet.getLastRowNum() + " "
              + row.getLastCellNum()); // 分别得到最后一行的行号，和一条记录的最后一个单元格
    }

    FileOutputStream out = new FileOutputStream(path); // 向d://test.xls中写数据
    row = sheet.createRow((short) (sheet.getLastRowNum() + 1)); // 在现有行号后追加数据
    row.createCell(1).setCellValue("append data"); // 设置第一个（从0开始）单元格的数据
    row.createCell(0).setCellValue(24); // 设置第二个（从0开始）单元格的数据

    out.flush();
    xssfWorkbook.write(out);
    out.close();
    System.out.println(row.getPhysicalNumberOfCells() + " "
            + row.getLastCellNum());
  }

  public void appendXls(String path) throws IOException {
    FileInputStream fs = new FileInputStream(path); // 获取d://test.xls
    POIFSFileSystem ps = new POIFSFileSystem(fs); // 使用POI提供的方法得到excel的信息
    HSSFWorkbook wb = new HSSFWorkbook(ps);
    HSSFSheet sheet = wb.getSheetAt(0); // 获取到工作表，因为一个excel可能有多个工作表
    HSSFRow row = sheet.getRow(0); // 获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
    System.out.println(sheet.getLastRowNum() + " " + row.getLastCellNum()); // 分别得到最后一行的行号，和一条记录的最后一个单元格

    FileOutputStream out = new FileOutputStream(path); // 向d://test.xls中写数据
    row = sheet.createRow((short) (sheet.getLastRowNum() + 1)); // 在现有行号后追加数据
    row.createCell(1).setCellValue("append data"); // 设置第一个（从0开始）单元格的数据
    row.createCell(0).setCellValue(24); // 设置第二个（从0开始）单元格的数据

    out.flush();
    wb.write(out);
    out.close();
    System.out.println(row.getPhysicalNumberOfCells() + " "
            + row.getLastCellNum());
  }

  /**
   *
   * @Title: writeXls
   * @Description: 写入xls
   * @param path
   *            参数说明
   * @return void 返回类型
   */
  public void writeXlsx(String path) {
    XSSFWorkbook workbook = null;
    workbook = new XSSFWorkbook();
    // 获取参数个数作为excel列数
    int columeCount = 2;
    // 获取List size作为excel行数
    XSSFSheet sheet = workbook.createSheet("sheet name");
    // 创建第一栏
    XSSFRow headRow = sheet.createRow(0);
    String[] titleArray = { "id", "name" };
    for (int m = 0; m <= columeCount - 1; m++) {
      XSSFCell cell = headRow.createCell(m);
      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
      sheet.setColumnWidth(m, 6000);
      XSSFCellStyle style = workbook.createCellStyle();
      XSSFFont font = workbook.createFont();
      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
      short color = HSSFColor.RED.index;
      font.setColor(color);
      style.setFont(font);
      // 填写数据
      cell.setCellStyle(style);
      cell.setCellValue(titleArray[m]);

    }
    List<Map<String, String>> list = new ArrayList ();
    Map<String, String> paraMap = null;
    for (int i = 0; i < 5; i++) {
      paraMap = new HashMap<String, String>();
      paraMap.put("id", i + "");
      paraMap.put("name", "test" + i);
      list.add(paraMap);
    }

    int index = 0;
    // 写入数据
    for (Map<String, String> entity : list) {
      // logger.info("写入一行");
      XSSFRow row = sheet.createRow(index + 1);
      for (int n = 0; n <= columeCount - 1; n++)
        row.createCell(n);
      row.getCell(0).setCellValue(entity.get("id"));
      row.getCell(1).setCellValue(entity.get("name"));
      index++;
    }

    // 写到磁盘上
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(new File(
              path));
      workbook.write(fileOutputStream);
      fileOutputStream.close();
      System.out.println("##################完成写入##################");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   * @Title: writeXls
   * @Description: 写入xls
   * @param path
   *            参数说明
   * @return void 返回类型
   */
  public static void writeXls(String path) {
    HSSFWorkbook workbook = null;
    workbook = new HSSFWorkbook();
    // 获取参数个数作为excel列数
    int columeCount = 2;
    // 获取List size作为excel行数
    HSSFSheet sheet = workbook.createSheet("sheet name");
    // 创建第一栏
    HSSFRow headRow = sheet.createRow(0);
    String[] titleArray = { "id", "name" };
    for (int m = 0; m <= columeCount - 1; m++) {
      HSSFCell cell = headRow.createCell(m);
      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
      sheet.setColumnWidth(m, 6000);
      HSSFCellStyle style = workbook.createCellStyle();
      HSSFFont font = workbook.createFont();
      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
      short color = HSSFColor.RED.index;
      font.setColor(color);
      style.setFont(font);
      // 填写数据
      cell.setCellStyle(style);
      cell.setCellValue(titleArray[m]);

    }
    List<Map<String, String>> list = new ArrayList ();
    Map<String, String> paraMap = null;
    for (int i = 0; i < 5; i++) {
      paraMap = new HashMap<String, String>();
      paraMap.put("id", i + "");
      paraMap.put("name", "test" + i);
      list.add(paraMap);
    }

    int index = 0;
    // 写入数据
    for (Map<String, String> entity : list) {
      // logger.info("写入一行");
      HSSFRow row = sheet.createRow(index + 1);
      for (int n = 0; n <= columeCount - 1; n++)
        row.createCell(n);
      row.getCell(0).setCellValue(entity.get("id"));
      row.getCell(1).setCellValue(entity.get("name"));
      index++;
    }

    // 写到磁盘上
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(new File(
              path));
      workbook.write(fileOutputStream);
      fileOutputStream.close();
      System.out.println("##################完成写入##################");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   * @Title: readXls
   * @Description: 读取xls
   * @param path
   * @throws FileNotFoundException
   * @throws IOException
   *             参数说明
   * @return void 返回类型
   */
  public void readXls(String path) throws FileNotFoundException, IOException {
    File file = new File(path);
    POIFSFileSystem poifsFileSystem = new POIFSFileSystem(
            new FileInputStream(file));
    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(poifsFileSystem);
    for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
      HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);

      if (hssfSheet == null) {
        continue;
      }
      String sheetName = hssfSheet.getSheetName();

      int rowstart = hssfSheet.getFirstRowNum();
      int rowEnd = hssfSheet.getLastRowNum();
      for (int i = rowstart; i <= rowEnd; i++) {
        HSSFRow row = hssfSheet.getRow(i);
        if (null == row)
          continue;
        int cellStart = row.getFirstCellNum();
        int cellEnd = row.getLastCellNum();

        for (int k = cellStart; k <= cellEnd; k++) {
          HSSFCell cell = row.getCell(k);
          if (null == cell)
            continue;
          switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
              System.out.print(cell.getNumericCellValue() + "   ");
              break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
              System.out.print(cell.getStringCellValue() + "   ");
              break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
              System.out.println(cell.getBooleanCellValue() + "   ");
              break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
              System.out.print(cell.getCellFormula() + "   ");
              break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
              System.out.println(" ");
              break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
              System.out.println(" ");
              break;
            default:
              System.out.print("未知类型   ");
              break;
          }

        }
        System.out.print("\n");
      }
    }
  }

  /**
   *
   * @param path
   *            路径
   * @return list
   * @throws IOException
   */
  public List<Map<String, String>> readXlsxRetList(String path)
          throws IOException {
    return readXlsxRetList(path, null, null, null, null, null, null);
  }

  /**
   *
   * @param path
   *            路径
   * @param sheetNo
   *            sheet页
   * @param rowstart
   *            行开始 默认0
   * @param rowEnd
   *            行结束
   * @param cellStart
   *            列开始 默认0
   * @param cellEnd
   *            列结束
   * @return list
   * @throws IOException
   */
  public List<Map<String, String>> readXlsxRetList(String path,
                                                   Integer sheetStartNo0, Integer sheetNo0, Integer rowstart0,
                                                   Integer rowEnd0, Integer cellStart0, Integer cellEnd0)
          throws IOException {

    // 读取文件流
    InputStream is = new FileInputStream(path);
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

    List<Map<String, String>> list = new ArrayList<Map<String, String>>();

    int sheetStartNo = sheetStartNo0 == null ? 0 : sheetStartNo0.intValue();

    int sheetNo = sheetNo0 == null ? xssfWorkbook.getNumberOfSheets()
            : sheetNo0.intValue();

    int rowstart;
    int rowEnd;

    int cellStart;
    int cellEnd;
    Map<String, String> map = null;
    // 读取每个sheet页
    for (int numSheet = sheetStartNo; numSheet < sheetNo; numSheet++) {
      XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
      if (xssfSheet == null) {
        continue;
      }

      rowstart = rowstart0 == null ? xssfSheet.getFirstRowNum()
              : rowstart0.intValue();// 开始行 第一行为0

      rowEnd = rowEnd0 == null ? xssfSheet.getLastRowNum() : rowEnd0
              .intValue(); // 结束行

      for (int i = rowstart; i <= rowEnd; i++) {
        map = new HashMap<String, String>();
        XSSFRow row = xssfSheet.getRow(i);
        if (null == row)
          continue;
        cellStart = cellStart0 == null ? row.getFirstCellNum()
                : cellStart0.intValue();// 开始列 第一列为0
        cellEnd = cellEnd0 == null ? row.getLastCellNum() : cellEnd0
                .intValue();// 结束列

        for (int k = cellStart; k <= cellEnd; k++) {
          XSSFCell cell = row.getCell(k);// 获取单元格值
          if (null == cell)
            continue;
          cell.setCellType(XSSFCell.CELL_TYPE_STRING);
          map.put(k + "", cell.getStringCellValue());

        }
        list.add(map);
        // System.out.print("\n");
      }
    }

    return list;
  }

  /**
   *
   * @Title: readXlsx
   * @Description: 读取xlsx
   * @throws IOException
   *             参数说明
   * @return void 返回类型
   */
  public void readXlsx(String path) throws IOException {

    // 读取文件流
    InputStream is = new FileInputStream(path);
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    // 读取每个sheet页
    for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
      XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
      if (xssfSheet == null) {
        continue;
      }

      int rowstart = xssfSheet.getFirstRowNum();// 开始行 第一行为0
      int rowEnd = xssfSheet.getLastRowNum(); // 结束行
      for (int i = rowstart; i <= rowEnd; i++) {
        XSSFRow row = xssfSheet.getRow(i);
        if (null == row)
          continue;
        int cellStart = row.getFirstCellNum();// 开始列 第一列为0
        int cellEnd = row.getLastCellNum();// 结束列

        for (int k = cellStart; k <= cellEnd; k++) {
          XSSFCell cell = row.getCell(k);// 获取单元格值
          if (null == cell)
            continue;

          switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数字
              System.out.print(cell.getNumericCellValue() + "   ");
              break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串
              System.out.print(cell.getStringCellValue() + "   ");
              break;
            case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
              System.out.println(cell.getBooleanCellValue() + "   ");
              break;
            case HSSFCell.CELL_TYPE_FORMULA: // 公式
              System.out.print(cell.getCellFormula() + "   ");
              break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
              System.out.println(" ");
              break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
              System.out.println(" ");
              break;
            default:
              System.out.print("未知类型   ");
              break;
          }
        }
        System.out.print("\n");
      }
    }

  }

}