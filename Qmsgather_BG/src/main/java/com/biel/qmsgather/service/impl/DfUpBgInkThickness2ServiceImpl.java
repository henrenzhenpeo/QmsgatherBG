package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.domain.DfUpBgInkThickness2;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.mapper.DfUpBgInkThicknessMapper;
import com.biel.qmsgather.service.DfUpBgInkThickness2Service;
import com.biel.qmsgather.mapper.DfUpBgInkThickness2Mapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_thickness2(bg油墨厚度)】的数据库操作Service实现
* @createDate 2025-02-27 16:28:07
*/
@Service
@Transactional
public class DfUpBgInkThickness2ServiceImpl extends ServiceImpl<DfUpBgInkThickness2Mapper, DfUpBgInkThickness2>
    implements DfUpBgInkThickness2Service{
    @Autowired
    private DfUpBgInkThicknessMapper dfUpBgInkThicknessMapper;

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
        String maxBatchId = dfUpBgInkThicknessMapper.getMaxBatchId();
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }








    @Override
    public boolean saveExcelWithJson(MultipartFile file, DfUpBgExcelDto baseInfo) {
        try {
            String newBatchId = getMaxBatchId();
            List<DfUpBgInkThickness2> dataList = parseExcelFile(file, baseInfo, newBatchId);
            return this.saveBatch(dataList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<DfUpBgInkThickness2> parseExcelFile(MultipartFile file, DfUpBgExcelDto baseInfo, String batchId) throws IOException {
        List<DfUpBgInkThickness2> resultList = new ArrayList<>();

        Workbook workbook;
        if (file.getOriginalFilename().endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (file.getOriginalFilename().endsWith(".xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new IllegalArgumentException("不支持的文件格式，仅支持.xls和.xlsx格式");
        }

        // 遍历所需的三个sheet页
        String[] sheetNames = {"膜厚"};
        for (String sheetName : sheetNames) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                processSheet(sheet, baseInfo, batchId, resultList);
            }
        }

        workbook.close();
        return resultList;
    }

    private void processSheet(Sheet sheet, DfUpBgExcelDto baseInfo, String batchId, List<DfUpBgInkThickness2> resultList) {
        FormulaEvaluator evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
        DataFormatter formatter = new DataFormatter();

        for (int i = 8; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || isEmptyRow(row)) continue;

            DfUpBgInkThickness2 entity = new DfUpBgInkThickness2();

            // 设置基础信息
            entity.setProcess(baseInfo.getProcess());
            entity.setFactory(baseInfo.getFactory());
            entity.setProject(baseInfo.getProject());
            entity.setColor(baseInfo.getColor());
            entity.setStage(baseInfo.getStage());
            entity.setTestDate(this.getCurrentDate());
            entity.setUpdateName(baseInfo.getUploader());
            entity.setBatchId(batchId);
            // entity.setSheetName(sheet.getSheetName()); // 添加sheet页名称

            // 从Excel行中读取数据
            // 使用getMergedCellValue处理可能合并的单元格


            // 从namePoint开始的所有字段赋值
            entity.setNamePoint(getCellValueAsString(row.getCell(0)));
             entity.setCoatingLayersLog("LOGO");
            entity.setTestTimeLog(getCellValueAsStrings(row.getCell(1)));
            entity.setTrueValueLog(getCellValueAsStrings(row.getCell(2)));
            entity.setStateLog(getCellValueAsString(row.getCell(3)));
            entity.setMachineCodeLog(getCellValueAsString(row.getCell(4)));

             entity.setCoatingLayersOne("一层");
            entity.setTestTimeOne(getCellValueAsStrings(row.getCell(5)));
            entity.setTrueValueOne(getCellValueAsStrings(row.getCell(6)));
            entity.setStateOne(getCellValueAsString(row.getCell(7)));
            entity.setMachineCodeOne(getCellValueAsString(row.getCell(8)));

             entity.setCoatingLayersTwo("二层");
            entity.setTestTimeTwo(getCellValueAsStrings(row.getCell(9)));
            entity.setTrueValueTwo(getCellValueAsStrings(row.getCell(10)));
            entity.setStateTwo(getCellValueAsString(row.getCell(11)));
            entity.setMachineCodeTwo(getCellValueAsString(row.getCell(12)));

             entity.setCoatingLayersThree("三层");
            entity.setTestTimeThree(getCellValueAsStrings(row.getCell(13)));
            entity.setTrueValueThree(getCellValueAsStrings(row.getCell(14)));
            entity.setStateThree(getCellValueAsString(row.getCell(15)));
            entity.setMachineCodeThree(getCellValueAsString(row.getCell(16)));

             entity.setCoatingLayersFour("四层");
            entity.setTestTimeFour(getCellValueAsStrings(row.getCell(17)));
            entity.setTrueValueFour(getCellValueAsStrings(row.getCell(18)));
            entity.setStateFour(getCellValueAsString(row.getCell(19)));
            entity.setMachineCodeFour(getCellValueAsString(row.getCell(20)));

             entity.setCoatingLayersFive("五层");
            entity.setTestTimeFive(getCellValueAsStrings(row.getCell(21)));
            entity.setTrueValueFive(getCellValueAsStrings(row.getCell(22)));
            entity.setStateFive(getCellValueAsString(row.getCell(23)));
            entity.setMachineCodeFive(getCellValueAsString(row.getCell(24)));

             entity.setCoatingLayersVarnish("光油");
            entity.setTestTimeVarnish(getCellValueAsStrings(row.getCell(25)));
            entity.setTrueValueVarnish(getCellValueAsStrings(row.getCell(26)));
            entity.setStateVarnish(getCellValueAsString(row.getCell(27)));
            entity.setMachineCodeVarnish(getCellValueAsString(row.getCell(28)));


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




    public static String getCurrentDate() {
        // 获取当前日期
        LocalDate today = LocalDate.now();

        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

        // 格式化日期并返回
        return today.format(formatter);
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




