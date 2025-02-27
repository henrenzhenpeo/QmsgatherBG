// package com.biel.qmsgather.read;
//
// import lombok.extern.slf4j.Slf4j;
// import org.apache.poi.openxml4j.util.ZipSecureFile;
// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.ss.util.CellRangeAddress;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import java.io.FileInputStream;
// import java.io.InputStream;
//
// /**
//  * @autor 96901
//  * @date 2025/2/25
//  */
//
// @Slf4j
// @SpringBootTest
// public class test10 {
//
//
//     @Test
//     public void testReadExcel() {
//         log.info("开始读取Excel文件...");
//
//         ZipSecureFile.setMinInflateRatio(0.001);
//         String filePath = "C:\\Users\\96901\\Desktop\\项目需求\\Excel导入文档的上传模板\\2\\2.BG现有上传模版\\等待\\YF-115  C98B 蓝色 9315喷砂 C98B-BG(DVT-MP蓝色9315大货 MIC喇叭孔）膜厚 11-4.xlsx";
//         String sheetName = "膜厚";
//         int startRow = 4;
//         int readColumns = 11; // 新增参数：指定要读取的列数
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
//                 StringBuilder rowData = new StringBuilder();
//                 // 遍历指定数量的列
//                 for (int j = 0; j < maxColumn; j++) {
//                     Cell cell = row == null ? null : row.getCell(j, Row.MissingCellPolicy.RETURN_NULL_AND_BLANK);
//                     String cellValue = getMergedCellValue(sheet, i, j, cell, evaluator, formatter);
//                     rowData.append(cellValue).append("\t");
//                 }
//                 log.info("第{}行: {}", i + 1, rowData.toString().trim());
//             }
//
//         } catch (Exception e) {
//             log.error("读取Excel文件出错: ", e);
//         }
//
//         log.info("Excel文件读取完成。");
//     }
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
//             return "null";
//         }
//     }
//
//     /**
//      * 获取单元格的值
//      */
//     private String getCellValue(Cell cell, FormulaEvaluator evaluator, DataFormatter formatter) {
//         if (cell == null) {
//             return "null";
//         }
//
//         try {
//             String value = formatter.formatCellValue(cell, evaluator).trim();
//             return value.isEmpty() ? "null" : value;
//         } catch (Exception e) {
//             log.error("获取单元格值时出错: ", e);
//             return "null";
//         }
//     }
//
//
//
// }
