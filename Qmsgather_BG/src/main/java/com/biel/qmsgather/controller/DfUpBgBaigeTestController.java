package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfUpBgBaigeTest;
import com.biel.qmsgather.domain.DfUpBgBaigeTestImg;
import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.service.DfUpBgBaigeMekService;
import com.biel.qmsgather.service.DfUpBgBaigeTestImgService;
import com.biel.qmsgather.service.DfUpBgBaigeTestService;
import com.biel.qmsgather.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/bg/dfUpBaigeTest")
@CrossOrigin
@Api(tags = "BG 百格测试/煮水百格接口")
public class DfUpBgBaigeTestController {


    @Autowired
    private DfUpBgBaigeTestService dfUpBgBaigeTestService;


    @Autowired
    private DfUpBgBaigeMekService dfUpBgBaigeMekService;

    @Autowired
    private DfUpBgBaigeTestImgService dfUpBgBaigeTestImgService;
    private final AtomicReference<String> sharedBatchId = new AtomicReference<>();

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${gateway.ipconfig}")
    private String ipconfig;

    @PostMapping("/upload")
    @ApiOperation(value = "BG 百格测试/煮水百格接口上传")
    public Result uploadDfUpBgBaigeTest(@RequestBody List<DfUpBgBaigeTest> dfUpBgBaigeTestList){

        // 从共享变量中获取 batch_id
        String batchId = sharedBatchId.get();
        if (batchId == null) {
            return new Result(500, "请先检查图片是否上传！");
        }

//        String newBatchId = dfUpBgBaigeTestService.getMaxBatchId();
//        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgBaigeTest dfUpBgBaigeTest : dfUpBgBaigeTestList) {
            dfUpBgBaigeTest.setBatchId(batchId);
        }

        boolean b = dfUpBgBaigeTestService.saveBatch(dfUpBgBaigeTestList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            // 清理 batch_id
            sharedBatchId.set(null);
            return new Result(200,"BG 百格测试/煮水百格接口上传成功");
        }

        return new Result(500,"BG 百格测试/煮水百格接口上传失败");
    }



    @PostMapping("/baigeUploadMultipleFiles")
    @ApiOperation(value = "BG 百格测试/煮水百格图片上传")
    public ResponseEntity<Object> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                                      @RequestParam("process") String process,
                                                      @RequestParam("testDate") String testDate) {
        String batchId = process + "-" + dfUpBgBaigeTestImgService.getMaxBatchId();
        sharedBatchId.set(batchId);

        // 创建以 batch_id 为名的文件夹
        Path batchDir = Paths.get(uploadDir, batchId);
        List<String> filePaths = new ArrayList<>();

        try {
            // 如果文件夹不存在，则创建
            if (!Files.exists(batchDir)) {
                Files.createDirectories(batchDir);
            }

            for (MultipartFile file : files) {
                if (file.isEmpty()) {
                    continue; // 跳过空文件
                }
                try {
                    byte[] bytes = file.getBytes();
                    Path path = batchDir.resolve(file.getOriginalFilename()); // 文件路径
                    Files.write(path, bytes);

                    // 构建图片信息对象并保存
                    DfUpBgBaigeTestImg dfUpBgBaigeTestImg = new DfUpBgBaigeTestImg();
                    dfUpBgBaigeTestImg.setImgAddress(path.toString()); // 修改为完整存储路径
                    dfUpBgBaigeTestImg.setImgNumber(file.getOriginalFilename());
                    dfUpBgBaigeTestImg.setProcess(process);
                    dfUpBgBaigeTestImg.setTestDate(testDate);
                    dfUpBgBaigeTestImg.setBatchId(batchId);
                    dfUpBgBaigeTestImgService.save(dfUpBgBaigeTestImg);

                    filePaths.add(ipconfig+path.toString()); // 添加到返回路径列表
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.badRequest().body("Failed to upload " + file.getOriginalFilename());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to create batch folder or upload files.");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Upload successful");
        response.put("batchId", batchId);
        response.put("filePaths", filePaths);

        return ResponseEntity.ok(response);
    }







    @PostMapping("/uploadExcelWithJson")
    @ApiOperation(value = "bg 百格测试和煮水百格接口上传Excel和JSON数据")
    public Result uploadExcelWithJson(@RequestParam("file") MultipartFile file,
                                      @RequestParam("jsonData") String jsonData) {
        try {

            String batchId = dfUpBgBaigeTestImgService.getMaxBatchId();
            // 解析JSON数据
            DfUpBgExcelDto baseInfo = new ObjectMapper().readValue(jsonData, DfUpBgExcelDto.class);

            String baseInfoStr = "ss";
            // 调用服务处理Excel和JSON数据
            int result = dfUpBgBaigeTestService.parseExcelFile(file, baseInfo, batchId);
          Map<String, Object> resultMap =  dfUpBgBaigeTestImgService.extractAndSaveImagesFromExcel(file, baseInfoStr, batchId);

            dfUpBgBaigeMekService.saveExcelWithJson(file, baseInfo);

            // 根据结果返回成功或失败
            return result > 0
                    ? new Result(200, "bg 百格测试和煮水百格接口上传Excel和JSON数据上传成功")
                    : new Result(500, "bg 百格测试和煮水百格接口上传Excel和JSON数据上传失败");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500, "bg 百格测试和煮水百格接口上传Excel和JSON数据数据上传失败: " + e.getMessage());
        }
    }



















}
