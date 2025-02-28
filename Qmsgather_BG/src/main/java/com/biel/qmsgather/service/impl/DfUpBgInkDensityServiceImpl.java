package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.mapper.DfUpBgInkDensityMapper;
import com.biel.qmsgather.service.DfUpBgInkDensityService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_density(油墨密度- OD)】的数据库操作Service实现
* @createDate 2024-12-10 17:31:13
*/
@Service
@Transactional
public class DfUpBgInkDensityServiceImpl extends ServiceImpl<DfUpBgInkDensityMapper, DfUpBgInkDensity>
    implements DfUpBgInkDensityService {

    @Autowired
    private DfUpBgInkDensityMapper dfUpBgInkDensityMapper;


    private String generateBatchId(String maxBatchId) {
        // 获取当天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());

        // 如果没有批次号，直接从 1 开始
        int newCount = 1;
        if (maxBatchId != null && maxBatchId.contains("+")) {
            String[] parts = maxBatchId.split("\\+");
            newCount = Integer.parseInt(parts[1]) + 1;
        }

        return today + "+" + newCount;
    }

    @Override
    public String getMaxBatchId() {
        String maxBatchId = dfUpBgInkDensityMapper.getMaxBatchId();

        // 生成新的批次号
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }





    @Override
    public boolean saveExcelWithJson(MultipartFile file, DfUpBgExcelDto baseInfo) {
        try {
            String newBatchId = getMaxBatchId();
            List<DfUpBgInkDensity> dataList = parseExcelFile(file, baseInfo, newBatchId);
            return this.saveBatch(dataList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<DfUpBgInkDensity> parseExcelFile(MultipartFile file, DfUpBgExcelDto baseInfo, String batchId) throws IOException {
        List<DfUpBgInkDensity> resultList = new ArrayList<>();

        Workbook workbook;
        if (file.getOriginalFilename().endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (file.getOriginalFilename().endsWith(".xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new IllegalArgumentException("不支持的文件格式，仅支持.xls和.xlsx格式");
        }

        // 遍历所需的三个sheet页
        String[] sheetNames = {"丝印前水滴角数据", "光油后水滴角数据", "AS后水滴角数据 "};
        for (String sheetName : sheetNames) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                processSheet(sheet, baseInfo, batchId, resultList);
            }
        }

        workbook.close();
        return resultList;
    }

    private void processSheet(Sheet sheet, DfUpBgExcelDto baseInfo, String batchId, List<DfUpBgInkDensity> resultList) {
        FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        DataFormatter formatter = new DataFormatter();

        for (int i = 4; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || isEmptyRow(row)) continue;

            DfUpBgInkDensity entity = new DfUpBgInkDensity();

            // 设置基础信息
            // entity.setProcess(baseInfo.getProcess());
            entity.setFactory(baseInfo.getFactory());
            entity.setProject(baseInfo.getProject());
            entity.setColor(baseInfo.getColor());
            entity.setStage(baseInfo.getStage());
            entity.setTestDate(baseInfo.getTestDate());
            // entity.setBatchId(baseInfo.getTestDate() + "-" + batchId);
            // entity.setSheetName(sheet.getSheetName()); // 添加sheet页名称


            // entity.setValue1(getCellValueAsDouble(row.getCell(8)));
            // entity.setValue2(getCellValueAsDouble(row.getCell(9)));
            // entity.setValue3(getCellValueAsDouble(row.getCell(10)));
            // entity.setValue4(getCellValueAsDouble(row.getCell(11)));
            // entity.setValue5(getCellValueAsDouble(row.getCell(12)));
            // entity.setValue6(getCellValueAsDouble(row.getCell(13)));
            // entity.setValue7(getCellValueAsDouble(row.getCell(14)));
            // entity.setValue8(getCellValueAsDouble(row.getCell(15)));
            // entity.setValue9(getCellValueAsDouble(row.getCell(16)));
            //
            // entity.setNoted(getCellValueAsString(row.getCell(18)));

            resultList.add(entity);
        }
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
            return "";
        }
        return formatter.formatCellValue(cell, evaluator);
    }




    private boolean isEmptyRow(Row row) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue());
                }
                return String.valueOf((int)cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return String.valueOf(cell.getNumericCellValue());
                } catch (Exception e) {
                    return cell.getStringCellValue();
                }
            default:
                return "";
        }
    }

    private Double getCellValueAsDouble(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return null;
                }
            case FORMULA:
                try {
                    return cell.getNumericCellValue();
                } catch (Exception e) {
                    try {
                        return Double.parseDouble(cell.getStringCellValue());
                    } catch (Exception ex) {
                        return null;
                    }
                }
            default:
                return null;
        }
    }



}




