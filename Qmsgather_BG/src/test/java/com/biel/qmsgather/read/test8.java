// package com.biel.qmsgather.read;
//
// import lombok.extern.slf4j.Slf4j;
// import org.apache.poi.hssf.usermodel.HSSFWorkbook;
// import org.apache.poi.openxml4j.util.ZipSecureFile;
// import org.apache.poi.poifs.filesystem.POIFSFileSystem;
// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.ss.util.CellRangeAddress;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.InputStream;
//
// /**
//  * @autor 96901
//  * @date 2025/2/25
//  */
//
// @SpringBootTest
// @Slf4j
// public class test8 {
//
//     @Test
//     public void testReadExcel() {
//         log.info("开始读取Excel文件...");
//
//         // 设置POI的安全限制
//         ZipSecureFile.setMinInflateRatio(0.001);
//
//         // 使用一个简单的本地测试文件路径，确保这个文件存在
//         String filePath = "C:\\Users\\96901\\Desktop\\项目需求\\Excel导入文档的上传模板\\2\\2.BG现有上传模版\\等待\\ss.xlsx";
//         String sheetName = "丝印前水滴角数据";
//         int startRow = 4;
//         int readColumns = 5;
//
//         try {
//             // 直接使用文件对象
//             File file = new File(filePath);
//
//             if (!file.exists()) {
//                 log.error("文件不存在: {}", filePath);
//                 return;
//             }
//
//             log.info("开始读取文件: {}", file.getAbsolutePath());
//
//             // 根据文件扩展名选择不同的方式打开工作簿
//             Workbook workbook = null;
//
//             try (InputStream is = new FileInputStream(file)) {
//                 if (filePath.toLowerCase().endsWith(".xls")) {
//                     // 对于.xls文件，使用POIFSFileSystem
//                     try (POIFSFileSystem fs = new POIFSFileSystem(is)) {
//                         workbook = new HSSFWorkbook(fs);
//                     }
//                 } else {
//                     // 对于.xlsx文件
//                     workbook = WorkbookFactory.create(is);
//                 }
//
//                 // 获取指定的Sheet，如果不存在则使用第一个Sheet
//                 Sheet sheet = null;
//                 if (sheetName != null && !sheetName.isEmpty()) {
//                     sheet = workbook.getSheet(sheetName);
//                 }
//                 if (sheet == null) {
//                     sheet = workbook.getSheetAt(0);
//                     log.info("使用第一个Sheet: {}", sheet.getSheetName());
//                 }
//
//                 // 创建公式计算器和数据格式化器
//                 FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//                 DataFormatter formatter = new DataFormatter();
//
//                 // 读取数据
//                 for (int rowIndex = startRow; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//                     Row row = sheet.getRow(rowIndex);
//                     if (row == null) continue;
//
//                     // 检查是否是空行
//                     boolean isEmptyRow = true;
//                     for (int i = 0; i < readColumns; i++) {
//                         Cell cell = row.getCell(i);
//                         if (cell != null && cell.getCellType() != CellType.BLANK) {
//                             isEmptyRow = false;
//                             break;
//                         }
//                     }
//                     if (isEmptyRow) continue;
//
//                     // 读取每一列的数据
//                     StringBuilder rowData = new StringBuilder();
//                     for (int colIndex = 0; colIndex < readColumns; colIndex++) {
//                         Cell cell = row.getCell(colIndex);
//                         String cellValue = getMergedCellValue(sheet, rowIndex, colIndex, cell, evaluator, formatter);
//                         rowData.append(cellValue).append("\t");
//                     }
//
//                     log.info("第{}行: {}", rowIndex + 1, rowData.toString());
//                 }
//             } finally {
//                 if (workbook != null) {
//                     workbook.close();
//                 }
//             }
//         } catch (Exception e) {
//             log.error("读取Excel文件出错: ", e);
//         }
//
//         log.info("Excel文件读取完成。");
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
// }
