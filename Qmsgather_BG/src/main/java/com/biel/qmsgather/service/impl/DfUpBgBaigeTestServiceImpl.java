package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgBaigeTest;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.service.DfUpBgBaigeTestService;
import com.biel.qmsgather.mapper.DfUpBgBaigeTestMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author dafenqi
* @description 针对表【df_up_bg_baige_test(bg 百格测试/煮水百格测试)】的数据库操作Service实现
* @createDate 2024-12-18 09:05:37
*/
@Service
@Transactional
public class DfUpBgBaigeTestServiceImpl extends ServiceImpl<DfUpBgBaigeTestMapper, DfUpBgBaigeTest>
    implements DfUpBgBaigeTestService{

    @Autowired
    private DfUpBgBaigeTestMapper dfUpBgBaigeTestMapper;

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
        String maxBatchId = dfUpBgBaigeTestMapper.getMaxBatchId();
        // 生成新的批次号
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }





    /**
     * 解析Excel文件并结合JSON数据生成实体列表，并保存到数据库
     * @param file Excel文件
     * @param baseInfo 基础信息
     * @param batchId 批次ID
     * @return 保存成功的记录数
     */
    public int parseExcelFile(MultipartFile file, DfUpBgExcelDto baseInfo, String batchId) throws IOException {
        List<DfUpBgBaigeTest> resultList = new ArrayList<>();

        // 创建工作簿
        Workbook workbook;
        if (file.getOriginalFilename().endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (file.getOriginalFilename().endsWith(".xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        } else {
            throw new IllegalArgumentException("不支持的文件格式，仅支持.xls和.xlsx格式");
        }

        // 处理所有工作表
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            String sheetName = sheet.getSheetName();

            // 只处理百格测试和煮水百格测试两个sheet
            if (sheetName.contains("百格测试") || sheetName.contains("煮水百格测试")) {
                // 从第4行开始读取数据（根据Excel格式）
                for (int i = 3; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null || isEmptyRow(row)) continue;

                    // 检查第一列是否有数据，如果没有则跳过
                    Cell firstCell = row.getCell(1);  // 2D码所在列
                    if (firstCell == null || firstCell.getCellType() == CellType.BLANK) {
                        continue;
                    }

                    DfUpBgBaigeTest entity = new DfUpBgBaigeTest();

                    // 设置JSON中的基础信息
                    entity.setProcess(baseInfo.getProcess());
                    entity.setFactory(baseInfo.getFactory());
                    entity.setProject(baseInfo.getProject());
                    entity.setColor(baseInfo.getColor());
                    entity.setStage(baseInfo.getStage());
                    entity.setTestDate(baseInfo.getTestDate());
                    entity.setUploadName(baseInfo.getUploader());
                    entity.setState("OK"); // 根据Excel中的结论设置

                    // 设置批次ID
                    entity.setBatchId(batchId);


                    // 从Excel行中读取数据
                    entity.setTwoCode(getCellValueAsString(row.getCell(1)));  // 2D码


                    entity.setTestTime(getCellValueAsString(row.getCell(2)));  // 时间


                    entity.setArea1(getCellValueAsString(row.getCell(3)));  // Area 1
                    entity.setArea2(getCellValueAsString(row.getCell(4)));  // Area 2
                    entity.setArea3(getCellValueAsString(row.getCell(5)));  // Area 3
                    entity.setArea4(getCellValueAsString(row.getCell(6)));  // Area 4
                    entity.setArea5(getCellValueAsString(row.getCell(7)));  // Area 5
                    entity.setArea6(getCellValueAsString(row.getCell(8)));  // Area 6
                    entity.setArea7(getCellValueAsString(row.getCell(9)));  // Area 7
                    entity.setConclusion(getCellValueAsString(row.getCell(10)));  // Conclusion

                    resultList.add(entity);
                }
            }
        }

        workbook.close();

        // 保存到数据库
        if (!resultList.isEmpty()) {
            // 在ServiceImpl中直接使用this.saveBatch方法
            boolean success = this.saveBatch(resultList);
            return success ? resultList.size() : 0;
        }

        return 0;
    }

    /**
     * 判断行是否为空
     */
    private boolean isEmptyRow(Row row) {
        if (row == null) return true;

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
                    // 如果是时间格式
                    return new SimpleDateFormat("HH:mm").format(cell.getDateCellValue());
                }
                // 对于数字，避免显示为科学计数法
                return new DecimalFormat("0").format(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return new DecimalFormat("0").format(cell.getNumericCellValue());
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




