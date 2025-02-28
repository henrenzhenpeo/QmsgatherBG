package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.mapper.DfUpBgInkLandHeightMapper;
import com.biel.qmsgather.service.DfUpBgInkLandHeightService;
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
* @description 针对表【df_up_bg_ink_land_height(油墨厚度+机油高度)】的数据库操作Service实现
* @createDate 2024-12-10 17:31:21
*/
@Service
@Transactional
public class DfUpBgInkLandHeightServiceImpl extends ServiceImpl<DfUpBgInkLandHeightMapper, DfUpBgInkLandHeight>
    implements DfUpBgInkLandHeightService {

    @Autowired
    private DfUpBgInkLandHeightMapper dfUpBgInkLandHeightMapper;


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
        String maxBatchId = dfUpBgInkLandHeightMapper.getMaxBatchId();

        // 生成新的批次号
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }





    @Override
    public boolean saveExcelWithJson(MultipartFile file, DfUpBgExcelDto baseInfo) {
        try {
            String newBatchId = getMaxBatchId();
            List<DfUpBgInkLandHeight> dataList = parseExcelFile(file, baseInfo, newBatchId);
            return this.saveBatch(dataList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<DfUpBgInkLandHeight> parseExcelFile(MultipartFile file, DfUpBgExcelDto baseInfo, String batchId) throws IOException {
        List<DfUpBgInkLandHeight> resultList = new ArrayList<>();

        Workbook workbook;
        if (file.getOriginalFilename().endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (file.getOriginalFilename().endsWith(".xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new IllegalArgumentException("不支持的文件格式，仅支持.xls和.xlsx格式");
        }

        // 遍历所需的三个sheet页
        String[] sheetNames = {"总厚+积油高度"};
        for (String sheetName : sheetNames) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                processSheet(sheet, baseInfo, batchId, resultList);
            }
        }

        workbook.close();
        return resultList;
    }

    private void processSheet(Sheet sheet, DfUpBgExcelDto baseInfo, String batchId, List<DfUpBgInkLandHeight> resultList) {
        FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        DataFormatter formatter = new DataFormatter();

        for (int i = 3; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || isEmptyRow(row)) continue;

            DfUpBgInkLandHeight entity = new DfUpBgInkLandHeight();

            // 设置基础信息

            entity.setFactory(baseInfo.getFactory());
            entity.setProject(baseInfo.getProject());
            entity.setColor(baseInfo.getColor());
            entity.setState(baseInfo.getStage());
            entity.setTestDate(baseInfo.getTestDate());
            entity.setBatchId(batchId);
            entity.setStage(baseInfo.getStage());
            entity.setUploadName(baseInfo.getUploader());

            // entity.setSheetName(sheet.getSheetName()); // 添加sheet页名称

            // 从Excel行中读取数据
            // 使用getMergedCellValue处理可能合并的单元格

            entity.setTestTime(getCellValueAsStrings(row.getCell(0)));
            entity.setInkHeight(getCellValueAsStrings(row.getCell(1)));

            entity.setOilHeight(getCellValueAsStrings(row.getCell(2)));
            entity.setExcelStage(getCellValueAsStrings(row.getCell(3)));


            resultList.add(entity);
        }
    }

    private String getCellValueAsStrings(Cell cell) {
        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
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




