package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgLiquidThrowing;
import com.biel.qmsgather.domain.DfUpBgSandBlast;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.service.DfUpBgSandBlastService;
import com.biel.qmsgather.mapper.DfUpBgSandBlastMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
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
* @description 针对表【df_up_bg_sand_blast(bg喷砂)】的数据库操作Service实现
* @createDate 2024-12-12 15:16:27
*/
@Service
@Transactional
public class DfUpBgSandBlastServiceImpl extends ServiceImpl<DfUpBgSandBlastMapper, DfUpBgSandBlast>
    implements DfUpBgSandBlastService{

    @Autowired
    private DfUpBgSandBlastMapper dfUpBgSandBlastMapper;

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
        String maxBatchId = dfUpBgSandBlastMapper.getMaxBatchId();
        // 生成新的批次号
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }










    // ... 现有代码 ...

    @Override
    public boolean saveExcelWithJson(MultipartFile file, DfUpBgExcelDto baseInfo) {
        try {
            // 获取新的批次ID
            String newBatchId = getMaxBatchId();

            // 解析Excel文件
            List<DfUpBgSandBlast> dataList = parseExcelFile(file, baseInfo, newBatchId);

            // 批量保存数据
            return this.saveBatch(dataList);




        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


















    /**
     * 解析Excel文件并结合JSON数据生成实体列表
     * @param file Excel文件
     * @param baseInfo 基础信息
     * @param batchId 批次ID
     * @return 实体列表
     */
    private List<DfUpBgSandBlast> parseExcelFile(MultipartFile file, DfUpBgExcelDto baseInfo, String batchId) throws IOException {
        List<DfUpBgSandBlast> resultList = new ArrayList<>();

        // 创建工作簿
        Workbook workbook;
        if (file.getOriginalFilename().endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (file.getOriginalFilename().endsWith(".xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new IllegalArgumentException("不支持的文件格式，仅支持.xls和.xlsx格式");
        }

        // 获取第一个工作表
        Sheet sheet = workbook.getSheetAt(0);

        // 从第二行开始读取数据（假设第一行是表头）
        for (int i = 4; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null || isEmptyRow(row)) continue;

            DfUpBgSandBlast entity = new DfUpBgSandBlast();

            // 设置JSON中的基础信息
            entity.setProcess(baseInfo.getProcess());
            entity.setFactory(baseInfo.getFactory());
            entity.setProject(baseInfo.getProject());
            entity.setColor(baseInfo.getColor());
            entity.setStage(baseInfo.getStage());
            entity.setTestDate(baseInfo.getTestDate());

            // 设置批次ID
            entity.setBatchId(batchId);

            // 从Excel行中读取数据
            // 注意：这里的列索引需要根据实际Excel文件的结构进行调整
            entity.setTestTime(getCellValueAsString(row.getCell(0)));

            entity.setDefinition(getCellValueAsDouble(row.getCell(1)));
            entity.setGranularity(getCellValueAsDouble(row.getCell(2)));
            entity.setGloss(getCellValueAsDouble(row.getCell(3)));
              entity.setMachineCode(getCellValueAsString(row.getCell(4)));
            entity.setState(getCellValueAsString(row.getCell(5)));
            entity.setUploadName(getCellValueAsString(row.getCell(6)));

            resultList.add(entity);
        }

        workbook.close();
        return resultList;
    }

    /**
     * 判断行是否为空
     */
    private boolean isEmptyRow(Row row) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取单元格的字符串值
     */
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 修改这里，使用包含时间的格式
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

    /**
     * 获取单元格的Double值
     */
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




