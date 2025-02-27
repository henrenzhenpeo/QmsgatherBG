// package com.biel.qmsgather.read;
//
// import com.biel.qmsgather.domain.DfYfExcelYf115C98bBlueSandblastingGridTestS1Data;
// import com.biel.qmsgather.mapper.DfYfExcelYf115C98bBlueSandblastingGridTestS1DataMapper;
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
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
//
// /**
//  * @autor 96901
//  * @date 2025/2/25
//  */
//
// @SpringBootTest
// @Slf4j
// public class test3 {
//     @Autowired
//     private DfYfExcelYf115C98bBlueSandblastingGridTestS1DataMapper mapper;
//
//     @Test
//     public void testReadExcel() {
//         log.info("开始读取Excel文件...");
//
//         ZipSecureFile.setMinInflateRatio(0.001);
//         String filePath = "C:\\Users\\96901\\Desktop\\项目需求\\Excel导入文档的上传模板\\2\\2.BG现有上传模版\\等待\\YF-115  C98B-9315喷砂 蓝色 DVT大货 MIC喇叭孔验证 百格 11-1 白(1).xlsx";
//         String sheetName = "百格测试";
//         int startRow = 4;
//         int readColumns = 11; // 指定要读取的列数
//
//         List<DfYfExcelYf115C98bBlueSandblastingGridTestS1Data> dataList = new ArrayList<>();
//         int successCount = 0;
//         int failCount = 0;
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
//                 try {
//                     DfYfExcelYf115C98bBlueSandblastingGridTestS1Data data = new DfYfExcelYf115C98bBlueSandblastingGridTestS1Data();
//
//                     // 设置实体类字段
//                     data.setTestNo(getMergedCellValue(sheet, i, 0, row.getCell(0), evaluator, formatter));
//                     data.setAreaNo(getMergedCellValue(sheet, i, 1, row.getCell(1), evaluator, formatter));
//                     data.setTestTime(getMergedCellValue(sheet, i, 2, row.getCell(2), evaluator, formatter));
//                     data.setArea1Value(getMergedCellValue(sheet, i, 3, row.getCell(3), evaluator, formatter));
//                     data.setArea2Value(getMergedCellValue(sheet, i, 4, row.getCell(4), evaluator, formatter));
//                     data.setArea3Value(getMergedCellValue(sheet, i, 5, row.getCell(5), evaluator, formatter));
//                     data.setArea4Value(getMergedCellValue(sheet, i, 6, row.getCell(6), evaluator, formatter));
//                     data.setArea5Value(getMergedCellValue(sheet, i, 7, row.getCell(7), evaluator, formatter));
//                     data.setArea6Value(getMergedCellValue(sheet, i, 8, row.getCell(8), evaluator, formatter));
//                     data.setArea7Value(getMergedCellValue(sheet, i, 9, row.getCell(9), evaluator, formatter));
//                     data.setTestResult(getMergedCellValue(sheet, i, 10, row.getCell(10), evaluator, formatter));
//
//                     // 设置创建时间和更新时间
//                     Date now = new Date();
//                     data.setCreateTime(now);
//                     data.setUpdateTime(now);
//
//                     // 检查数据是否有效（至少有一个非null字段）
//                     if (isValidData(data)) {
//                         dataList.add(data);
//                         log.info("第{}行数据有效: {}", i + 1, data);
//                     } else {
//                         log.warn("第{}行数据无效，跳过", i + 1);
//                     }
//                 } catch (Exception e) {
//                     failCount++;
//                     log.error("处理第{}行数据时出错: {}", i + 1, e.getMessage());
//                 }
//             }
//
//             // 批量保存数据到数据库
//             if (!dataList.isEmpty()) {
//                 for (DfYfExcelYf115C98bBlueSandblastingGridTestS1Data data : dataList) {
//                     try {
//                         mapper.insert(data);
//                         successCount++;
//                     } catch (Exception e) {
//                         failCount++;
//                         log.error("保存数据失败: {}", e.getMessage());
//                     }
//                 }
//             }
//
//             log.info("Excel数据导入完成，成功: {}条，失败: {}条", successCount, failCount);
//
//         } catch (Exception e) {
//             log.error("读取Excel文件出错: ", e);
//         }
//
//         log.info("Excel文件读取完成。");
//     }
//
//     /**
//      * 检查数据是否有效（至少有一个非null或非"null"字段）
//      */
//     private boolean isValidData(DfYfExcelYf115C98bBlueSandblastingGridTestS1Data data) {
//         return (isValidField(data.getTestNo()) ||
//                 isValidField(data.getAreaNo()) ||
//                 isValidField(data.getTestTime()) ||
//                 isValidField(data.getArea1Value()) ||
//                 isValidField(data.getArea2Value()) ||
//                 isValidField(data.getArea3Value()) ||
//                 isValidField(data.getArea4Value()) ||
//                 isValidField(data.getArea5Value()) ||
//                 isValidField(data.getArea6Value()) ||
//                 isValidField(data.getArea7Value()) ||
//                 isValidField(data.getTestResult()));
//     }
//
//     /**
//      * 检查字段是否有效（非null且非"null"字符串）
//      */
//     private boolean isValidField(String field) {
//         return field != null && !field.equals("null") && !field.trim().isEmpty();
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
// }
//
//
//
