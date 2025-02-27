package com.biel.qmsgather.read;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @autor 96901
 * @date 2025/2/18
 */

@Slf4j
@SpringBootTest
public class test1 {


    @Test
    public void testReadExcel() {
        log.info("Starting to read Excel file...");

        // 设置ZIP炸弹保护的阈值
        ZipSecureFile.setMinInflateRatio(-1.0d);
        // 禁用ZIP炸弹检测
        ZipSecureFile.setMaxEntrySize(Long.MAX_VALUE);

        // 使用规范化的文件路径
        String filePath = "C:" + File.separator + "Users" + File.separator + "96901" +
                File.separator + "Desktop" + File.separator + "项目需求" +
                File.separator + "Excel导入文档的上传模板" + File.separator + "2" +
                File.separator + "2.BG现有上传模版" + File.separator + "等待" +
                File.separator + "ss.xlsx";

        String sheetName = "丝印前水滴角数据";
        int startRow = 0;
        int endRow = 5;

        File excelFile = new File(filePath);
        if (!excelFile.exists()) {
            log.error("文件不存在: {}", filePath);
            return;
        }

        try (FileInputStream file = new FileInputStream(excelFile)) {
            // 使用try-with-resources确保资源正确关闭
            Workbook workbook = null;
            try {
                workbook = WorkbookFactory.create(file);
            } catch (Exception e) {
                log.error("创建工作簿时出错: {}", e.getMessage());
                return;
            }

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                log.error("未找到工作表: {}", sheetName);
                workbook.close();
                return;
            }

            // ... 其余代码保持不变 ...
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            DataFormatter formatter = new DataFormatter();

            for (int i = startRow; i <= endRow; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    log.warn("第 {} 行为空，跳过...", i);
                    continue;
                }

                StringBuilder rowData = new StringBuilder();
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = getMergedCellValue(sheet, i, j, cell, evaluator, formatter);
                    rowData.append(cellValue).append("\t");
                }
                log.info("第 {} 行: {}", i, rowData.toString().trim());
            }

            workbook.close();
        } catch (IOException e) {
            log.error("读取Excel文件时出错: {}", e.getMessage(), e);
        } catch (Exception e) {
            log.error("处理Excel文件时发生未知错误: {}", e.getMessage(), e);
        }

        log.info("完成Excel文件读取。");
    }
    private String getMergedCellValue(Sheet sheet, int row, int col, Cell cell, FormulaEvaluator evaluator, DataFormatter formatter) {
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

    private String getCellValue(Cell cell, FormulaEvaluator evaluator, DataFormatter formatter) {
        if (cell == null) {
            return "null";
        }

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
    }
}