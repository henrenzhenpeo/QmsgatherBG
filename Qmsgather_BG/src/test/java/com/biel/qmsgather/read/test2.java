package com.biel.qmsgather.read;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor 96901
 * @date 2025/2/25
 */

@SpringBootTest
@Slf4j
public class test2 {

    @Test
    public void testReadExcel() {
        log.info("开始读取Excel文件...");

        ZipSecureFile.setMinInflateRatio(0.001);
        String filePath = "C:\\Users\\96901\\Desktop\\项目需求\\Excel导入文档的上传模板\\难弄\\C98B-油墨厚度（1）.xlsx";
        String sheetName = "BM2";
        int startRow = 4; // 从第5行开始（索引从0开始）

        try (FileInputStream file = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                log.error("未找到工作表: {}", sheetName);
                return;
            }

            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            DataFormatter formatter = new DataFormatter();

            // 遍历从第5行开始的所有行
            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (isEmptyRow(row)) {
                    continue; // 跳过空行
                }

                StringBuilder rowData = new StringBuilder();
                // 遍历行中的单元格
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = getMergedCellValue(sheet, i, j, cell, evaluator, formatter);
                    rowData.append(cellValue).append("\t");
                }
                log.info("第{}行: {}", i + 1, rowData.toString().trim());
            }
        } catch (IOException e) {
            log.error("读取Excel文件出错: {}", e.getMessage(), e);
        }

        log.info("Excel文件读取完成。");
    }

    /**
     * 检查行是否为空
     */
    private boolean isEmptyRow(Row row) {
        if (row == null) {
            return true;
        }

        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                String value = cell.toString().trim();
                if (!value.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取合并单元格的值
     */
    private String getMergedCellValue(Sheet sheet, int row, int col, Cell cell, FormulaEvaluator evaluator, DataFormatter formatter) {
        // 检查是否是合并单元格
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress region = sheet.getMergedRegion(i);
            if (region.isInRange(row, col)) {
                // 获取合并区域的第一个单元格的值
                Cell firstCell = sheet.getRow(region.getFirstRow()).getCell(region.getFirstColumn());
                return getCellValue(firstCell, evaluator, formatter);
            }
        }
        return getCellValue(cell, evaluator, formatter);
    }

    /**
     * 获取单元格的值
     */
    private String getCellValue(Cell cell, FormulaEvaluator evaluator, DataFormatter formatter) {
        if (cell == null) {
            return "null";
        }

        try {
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getLocalDateTimeCellValue().toString();
                    } else {
                        // 使用DataFormatter来保持Excel中的格式
                        return formatter.formatCellValue(cell);
                    }
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    try {
                        // 使用FormulaEvaluator计算公式结果
                        return formatter.formatCellValue(cell, evaluator);
                    } catch (Exception e) {
                        // 如果公式计算失败，则返回公式字符串
                        return cell.getCellFormula();
                    }
                case BLANK:
                    return "";
                case ERROR:
                    return "ERROR: " + cell.getErrorCellValue();
                default:
                    return "unknown";
            }
        } catch (Exception e) {
            log.error("获取单元格值时出错: {}", e.getMessage());
            return "ERROR";
        }
    }












}
