// package com.biel.qmsgather.read;
//
// import com.biel.qmsgather.domain.DfYfExcelYf115C98bBlueSandblastingFilmThicknessS1Data;
// import com.biel.qmsgather.mapper.DfYfExcelYf115C98bBlueSandblastingFilmThicknessS1DataMapper;
// import lombok.extern.slf4j.Slf4j;
// import org.apache.poi.openxml4j.util.ZipSecureFile;
// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.ss.util.CellRangeAddress;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import java.io.FileInputStream;
// import java.io.InputStream;
// import java.math.BigDecimal;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
//
// /**
//  * @autor 96901
//  * @date 2025/2/25
//  */
//
// @Slf4j
// @SpringBootTest
// public class test9 {
//
//     @Autowired
//     private DfYfExcelYf115C98bBlueSandblastingFilmThicknessS1DataMapper dataMapper;
//
//     @Test
//     public void testReadExcelAndSaveToDb() {
//         log.info("开始读取Excel文件并保存到数据库...");
//
//         ZipSecureFile.setMinInflateRatio(0.001);
//         String filePath = "C:\\Users\\96901\\Desktop\\项目需求\\Excel导入文档的上传模板\\2\\2.BG现有上传模版\\等待\\YF-115  C98B 蓝色 9315喷砂 C98B-BG(DVT-MP蓝色9315大货 MIC喇叭孔）膜厚 11-4.xlsx";
//         String sheetName = "膜厚";
//         int startRow = 8;
//         int readColumns = 29; // 指定要读取的列数
//
//         List<DfYfExcelYf115C98bBlueSandblastingFilmThicknessS1Data> dataList = new ArrayList<>();
//
//         try (InputStream is = new FileInputStream(filePath);
//              Workbook workbook = new XSSFWorkbook(is)) {
//
//             Sheet sheet = workbook.getSheet(sheetName);
//             if (sheet == null) {
//                 log.error("未找到工作表: {}", sheetName);
//                 return;
//             }
//
//             FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//             DataFormatter formatter = new DataFormatter();
//
//             // 获取最大列数，但不超过指定的列数
//             int maxColumn = Math.min(getMaxColumn(sheet), readColumns);
//
//             // 遍历从指定行开始的所有行
//             for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
//                 Row row = sheet.getRow(i);
//                 if (isEmptyRow(row, maxColumn)) {
//                     continue;
//                 }
//
//                 // 创建实体类对象
//                 DfYfExcelYf115C98bBlueSandblastingFilmThicknessS1Data data = new DfYfExcelYf115C98bBlueSandblastingFilmThicknessS1Data();
//
//                 // 设置创建时间和更新时间
//                 Date now = new Date();
//                 data.setCreateTime(now);
//                 data.setUpdateTime(now);
//
//                 // 读取每一列的数据并映射到实体类
//                 for (int j = 0; j < maxColumn; j++) {
//                     Cell cell = row == null ? null : row.getCell(j, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
//                     String cellValue = getMergedCellValue(sheet, i, j, cell, evaluator, formatter);
//
//                     // 根据列索引将数据映射到实体类的对应字段
//                     mapCellValueToEntity(data, j, cellValue);
//                 }
//
//                 // 添加到列表
//                 dataList.add(data);
//                 log.info("读取到数据: {}", data);
//             }
//
//             // 批量保存到数据库
//             if (!dataList.isEmpty()) {
//                 for (DfYfExcelYf115C98bBlueSandblastingFilmThicknessS1Data data : dataList) {
//                     dataMapper.insert(data);
//                 }
//                 log.info("成功保存 {} 条数据到数据库", dataList.size());
//             }
//
//         } catch (Exception e) {
//             log.error("读取Excel文件或保存数据出错: ", e);
//         }
//
//         log.info("Excel文件读取和数据保存完成。");
//     }
//
//     /**
//      * 将单元格值映射到实体类的对应字段
//      */
//     private void mapCellValueToEntity(DfYfExcelYf115C98bBlueSandblastingFilmThicknessS1Data data, int columnIndex, String cellValue) {
//         if (cellValue == null || "null".equals(cellValue) || cellValue.trim().isEmpty()) {
//             return; // 跳过空值
//         }
//
//         try {
//             switch (columnIndex) {
//                 case 0: // 点位
//                     data.setPositionName(cellValue);
//                     break;
//                 case 1: // 第一层时间
//                     data.setTime1(parseDate(cellValue));
//                     break;
//                 case 2: // 第一层首值
//                     data.setValue1(parseDecimal(cellValue));
//                     break;
//                 case 3: // 第一层状态
//                     data.setStatus1(cellValue);
//                     break;
//                 case 4: // 第一层批台
//                     data.setBatch1(cellValue);
//                     break;
//                 case 5: // 第二层时间
//                     data.setTime2(parseDate(cellValue));
//                     break;
//                 case 6: // 第二层首值
//                     data.setValue2(parseDecimal(cellValue));
//                     break;
//                 case 7: // 第二层状态
//                     data.setStatus2(cellValue);
//                     break;
//                 case 8: // 第二层批台
//                     data.setBatch2(cellValue);
//                     break;
//                 case 9: // 第三层时间
//                     data.setTime3(parseDate(cellValue));
//                     break;
//                 case 10: // 第三层首值
//                     data.setValue3(parseDecimal(cellValue));
//                     break;
//                 case 11: // 第三层状态
//                     data.setStatus3(cellValue);
//                     break;
//                 case 12: // 第三层批台
//                     data.setBatch3(cellValue);
//                     break;
//                 case 13: // 第四层时间
//                     data.setTime4(parseDate(cellValue));
//                     break;
//                 case 14: // 第四层首值
//                     data.setValue4(parseDecimal(cellValue));
//                     break;
//                 case 15: // 第四层状态
//                     data.setStatus4(cellValue);
//                     break;
//                 case 16: // 第四层批台
//                     data.setBatch4(cellValue);
//                     break;
//                 case 17: // 第五层时间
//                     data.setTime5(parseDate(cellValue));
//                     break;
//                 case 18: // 第五层首值
//                     data.setValue5(parseDecimal(cellValue));
//                     break;
//                 case 19: // 第五层状态
//                     data.setStatus5(cellValue);
//                     break;
//                 case 20: // 第五层批台
//                     data.setBatch5(cellValue);
//                     break;
//                 case 21: // 第六层时间
//                     data.setTime6(parseDate(cellValue));
//                     break;
//                 case 22: // 第六层首值
//                     data.setValue6(parseDecimal(cellValue));
//                     break;
//                 case 23: // 第六层状态
//                     data.setStatus6(cellValue);
//                     break;
//                 case 24: // 第六层批台
//                     data.setBatch6(cellValue);
//                     break;
//                 case 25: // 光油层时间
//                     data.setTime7(parseDate(cellValue));
//                     break;
//                 case 26: // 光油层首值
//                     data.setValue7(parseDecimal(cellValue));
//                     break;
//                 case 27: // 光油层状态
//                     data.setStatus7(cellValue);
//                     break;
//                 case 28: // 光油层批台
//                     data.setBatch7(cellValue);
//                     break;
//                 default:
//                     break;
//             }
//         } catch (Exception e) {
//             log.error("映射数据时出错，列索引: {}, 值: {}, 错误: {}", columnIndex, cellValue, e.getMessage());
//         }
//     }
//
//     /**
//      * 解析日期字符串为Date对象
//      */
//     private Date parseDate(String dateStr) {
//         if (dateStr == null || "null".equals(dateStr) || dateStr.trim().isEmpty()) {
//             return null;
//         }
//
//         try {
//             // 首先尝试解析时间格式 (HH:mm)
//             if (dateStr.contains(":")) {
//                 SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//                 return timeFormat.parse(dateStr);
//             }
//
//             // 尝试日期格式
//             SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//             return sdf.parse(dateStr);
//         } catch (Exception e) {
//             try {
//                 // 尝试其他可能的日期格式
//                 SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//                 return sdf2.parse(dateStr);
//             } catch (Exception ex) {
//                 log.error("解析日期失败: {}", dateStr);
//                 return null;
//             }
//         }
//     }
//
//     /**
//      * 解析数字字符串为BigDecimal
//      */
//     private BigDecimal parseDecimal(String numStr) {
//         if (numStr == null || "null".equals(numStr) || numStr.trim().isEmpty()) {
//             return null;
//         }
//
//         try {
//             return new BigDecimal(numStr.replace(",", ""));
//         } catch (Exception e) {
//             log.error("解析数字失败: {}", numStr);
//             return null;
//         }
//     }
//
//     /**
//      * 获取sheet中的最大列数
//      */
//     private int getMaxColumn(Sheet sheet) {
//         int maxColumn = 0;
//         for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
//             Row row = sheet.getRow(i);
//             if (row != null) {
//                 maxColumn = Math.max(maxColumn, row.getLastCellNum());
//             }
//         }
//         return maxColumn;
//     }
//
//     /**
//      * 检查行是否为空
//      */
//     private boolean isEmptyRow(Row row, int maxColumn) {
//         if (row == null) {
//             return true;
//         }
//
//         for (int i = 0; i < maxColumn; i++) {
//             Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
//             if (cell != null && cell.getCellType() != CellType.BLANK) {
//                 String value = cell.toString().trim();
//                 if (!value.isEmpty()) {
//                     return false;
//                 }
//             }
//         }
//         return true;
//     }
//
//     /**
//      * 获取合并单元格的值
//      */
//     private String getMergedCellValue(Sheet sheet, int row, int col, Cell cell, FormulaEvaluator evaluator, DataFormatter formatter) {
//         try {
//             // 检查是否是合并单元格
//             for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
//                 CellRangeAddress region = sheet.getMergedRegion(i);
//                 if (region.isInRange(row, col)) {
//                     // 获取合并区域的第一个单元格的值
//                     Cell firstCell = sheet.getRow(region.getFirstRow()).getCell(region.getFirstColumn());
//                     return getCellValue(firstCell, evaluator, formatter);
//                 }
//             }
//             return getCellValue(cell, evaluator, formatter);
//         } catch (Exception e) {
//             log.error("获取合并单元格值时出错: ", e);
//             return null;
//         }
//     }
//
//     /**
//      * 获取单元格的值
//      */
//     private String getCellValue(Cell cell, FormulaEvaluator evaluator, DataFormatter formatter) {
//         if (cell == null) {
//             return null;
//         }
//
//         try {
//             String value = formatter.formatCellValue(cell, evaluator).trim();
//             return value.isEmpty() ? null : value;
//         } catch (Exception e) {
//             log.error("获取单元格值时出错: ", e);
//             return null;
//         }
//     }
//
// }
