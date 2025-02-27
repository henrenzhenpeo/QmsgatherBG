package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgBaigeTest;
import com.biel.qmsgather.domain.DfUpBgBaigeTestImg;
import com.biel.qmsgather.domain.DfUpBgLiquidThrowing;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.service.DfUpBgBaigeTestImgService;
import com.biel.qmsgather.mapper.DfUpBgBaigeTestImgMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
* @author dafenqi
* @description 针对表【df_up_bg_baige_test_img(bg百格测试图片上传)】的数据库操作Service实现
* @createDate 2024-12-24 10:47:58
*/
@Service
public class DfUpBgBaigeTestImgServiceImpl extends ServiceImpl<DfUpBgBaigeTestImgMapper, DfUpBgBaigeTestImg>
    implements DfUpBgBaigeTestImgService{

    @Autowired
    private DfUpBgBaigeTestImgMapper dfUpBgBaigeTestImgMapper;

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
        String maxBatchId = dfUpBgBaigeTestImgMapper.getMaxBatchId();
        // 生成新的批次号
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }




    // @Value("${file.upload-dir}")
    // private String uploadDir;
    //
    // @Value("${ipconfig}")
    // private String ipconfig;

    // 用于在方法间共享批次ID
    private final AtomicReference<String> sharedBatchId = new AtomicReference<>();

    /**
     * 从Excel文件中提取图片并保存
     * @param excelFile Excel文件
     * @param process 工序
     * @param testDate 测试日期
     * @return 处理结果
     */
    public Map<String, Object> extractAndSaveImagesFromExcel(MultipartFile excelFile, String process, String testDate) throws IOException {
        // 生成批次ID
        String batchId = process + "-" + getMaxBatchId();
        sharedBatchId.set(batchId);

        String uploadDir ="C:\\Users\\96901\\Desktop\\项目需求\\Excel导入文档的上传模板\\2\\2.BG现有上传模版";

        // 创建以batch_id为名的文件夹
        Path batchDir = Paths.get(uploadDir, batchId);
        List<String> filePaths = new ArrayList<>();

        try {
            // 如果文件夹不存在，则创建
            if (!Files.exists(batchDir)) {
                Files.createDirectories(batchDir);
            }

            // 打开Excel文件
            Workbook workbook;
            if (excelFile.getOriginalFilename().endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(excelFile.getInputStream());
            } else if (excelFile.getOriginalFilename().endsWith(".xls")) {
                workbook = new HSSFWorkbook(excelFile.getInputStream());
            } else {
                throw new IllegalArgumentException("不支持的文件格式，仅支持.xls和.xlsx格式");
            }

            // 处理所有工作表
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                String sheetName = sheet.getSheetName();

                // 只处理百格测试和煮水百格测试两个sheet
                if (sheetName.contains("百格测试") || sheetName.contains("煮水百格")) {
                    // 提取该sheet中的所有图片
                    List<PictureData> pictures = extractPictures(workbook, sheetIndex);

                    // 保存图片
                    for (int i = 0; i < pictures.size(); i++) {
                        PictureData picture = pictures.get(i);
                        byte[] data = picture.getData();

                        // 生成图片文件名
                        String extension = getImageExtension(picture);
                        String fileName = sheetName + "_image_" + (i + 1) + "." + extension;

                        // 保存图片到文件系统
                        Path imagePath = batchDir.resolve(fileName);
                        Files.write(imagePath, data);

                        // 构建图片信息对象并保存到数据库
                        DfUpBgBaigeTestImg imgInfo = new DfUpBgBaigeTestImg();
                        imgInfo.setImgAddress(imagePath.toString());
                        imgInfo.setImgNumber(fileName);
                        imgInfo.setProcess(process);
                        imgInfo.setTestDate(testDate);
                        imgInfo.setBatchId(batchId);
                        save(imgInfo); // 使用ServiceImpl的save方法

                        filePaths.add( imagePath.toString());
                    }
                }
            }

            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("提取或保存图片时出错: " + e.getMessage());
        }

        // 返回处理结果
        Map<String, Object> response = new HashMap<>();
        response.put("message", "图片提取并上传成功");
        response.put("batchId", batchId);
        response.put("filePaths", filePaths);
        response.put("totalImages", filePaths.size());

        return response;
    }

    /**
     * 从工作簿中提取图片
     */
    private List<PictureData> extractPictures(Workbook workbook, int sheetIndex) {
        List<PictureData> pictures = new ArrayList<>();

        if (workbook instanceof XSSFWorkbook) {
            XSSFWorkbook xssfWorkbook = (XSSFWorkbook) workbook;
            XSSFSheet sheet = xssfWorkbook.getSheetAt(sheetIndex);

            // 获取图片与单元格的映射关系
            for (POIXMLDocumentPart dr : sheet.getRelations()) {
                if (dr instanceof XSSFDrawing) {
                    XSSFDrawing drawing = (XSSFDrawing) dr;
                    List<XSSFShape> shapes = drawing.getShapes();

                    for (XSSFShape shape : shapes) {
                        if (shape instanceof XSSFPicture) {
                            XSSFPicture pic = (XSSFPicture) shape;
                            XSSFPictureData picData = pic.getPictureData();
                            pictures.add(picData);
                        }
                    }
                }
            }
        } else if (workbook instanceof HSSFWorkbook) {
            HSSFWorkbook hssfWorkbook = (HSSFWorkbook) workbook;
            HSSFSheet sheet = hssfWorkbook.getSheetAt(sheetIndex);

            // 获取图片与单元格的映射关系
            HSSFPatriarch patriarch = sheet.getDrawingPatriarch();
            if (patriarch != null) {
                List<HSSFShape> shapes = patriarch.getChildren();

                for (HSSFShape shape : shapes) {
                    if (shape instanceof HSSFPicture) {
                        HSSFPicture pic = (HSSFPicture) shape;
                        HSSFPictureData picData = pic.getPictureData();
                        pictures.add(picData);
                    }
                }
            }
        }

        return pictures;
    }

    /**
     * 根据图片数据获取图片扩展名
     */
    private String getImageExtension(PictureData picture) {
        int format = picture.getPictureType();
        switch (format) {
            case Workbook.PICTURE_TYPE_JPEG:
                return "jpg";
            case Workbook.PICTURE_TYPE_PNG:
                return "png";
            case Workbook.PICTURE_TYPE_DIB:
                return "dib";
            case Workbook.PICTURE_TYPE_EMF:
                return "emf";
            case Workbook.PICTURE_TYPE_WMF:
                return "wmf";
            case Workbook.PICTURE_TYPE_PICT:
                return "pict";
            default:
                return "jpg";
        }
    }

    /**
     * 获取最大批次ID
     */
    // public String getMaxBatchId() {
    //     // 这里应该实现获取最大批次ID的逻辑
    //     // 可以从数据库中查询当前最大的批次ID，然后加1
    //     // 这里简化为使用时间戳
    //     return String.valueOf(System.currentTimeMillis());
    // }













































}




