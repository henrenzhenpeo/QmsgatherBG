package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgBaigeMek;
import com.biel.qmsgather.domain.DfUpBgBaigeTest;
import com.biel.qmsgather.domain.DfUpBgBaigeTestImg;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.service.DfUpBgBaigeMekService;
import com.biel.qmsgather.service.DfUpBgBaigeTestImgService;
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
@RequestMapping("/bg/dfUpBaigeMek")
@CrossOrigin
@Api(tags = "BG MEK百格接口")
public class DfUpBgBaigeMekController {

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
    @ApiOperation(value = "BG MEK百格接口上传")
    public Result uploadDfUpBgBaigeTest(@RequestBody List<DfUpBgBaigeMek> dfUpBgBaigeMekList){
        // 从共享变量中获取 batch_id
        String batchId = sharedBatchId.get();
        if (batchId == null) {
            return new Result(500, "请先检查图片是否上传！");
        }
//        String newBatchId = dfUpBgBaigeMekService.getMaxBatchId();
//        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgBaigeMek dfUpBgBaigeMek : dfUpBgBaigeMekList) {
            dfUpBgBaigeMek.setBatchId(batchId);
        }

        boolean b = dfUpBgBaigeMekService.saveBatch(dfUpBgBaigeMekList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            // 清理 batch_id
            sharedBatchId.set(null);
            return new Result(200,"BG MEK百格接口上传成功");
        }

        return new Result(500,"BG MEK百格接口上传失败");
    }

    @PostMapping("/mekUploadMultipleFiles")
    @ApiOperation(value = "BG MEK百格图片上传")
    public ResponseEntity<Object> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                                      @RequestParam("process") String process,
                                                      @RequestParam("production") String production,
                                                      @RequestParam("testDate") String testDate) {
        String batchId = production+"-"+process+"-"+dfUpBgBaigeTestImgService.getMaxBatchId();
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
        response.put("message", "MEK百格测试上传成功");
        response.put("batchId", batchId);
        response.put("filePaths", filePaths);

        return ResponseEntity.ok(response);
    }





    @PostMapping("/uploadExcelWithJson")
    @ApiOperation(value = "bg MEK接口上传Excel和JSON数据")
    public Result uploadExcelWithJson(@RequestParam("file") MultipartFile file,
                                      @RequestParam("jsonData") String jsonData) {
        try {

            String batchId = dfUpBgBaigeTestImgService.getMaxBatchId();
            // 解析JSON数据
            DfUpBgExcelDto baseInfo = new ObjectMapper().readValue(jsonData, DfUpBgExcelDto.class);

            // 调用服务处理Excel和JSON数据
            boolean result = dfUpBgBaigeMekService.saveExcelWithJson(file, baseInfo);

            // 根据结果返回成功或失败
            return result
                    ? new Result(200, "bg 液抛Excel和JSON数据上传成功")
                    : new Result(500, "bg 液抛Excel和JSON数据上传失败");

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500, "bg 液抛Excel和JSON数据上传失败: " + e.getMessage());
        }
    }


    @GetMapping("/findDfUpBaigeMek")
    @ApiOperation(value = "BG 百格Mek查询接口")
    public R findDfUpBaigeMek(
            @RequestParam(value = "process", required = false) String process,
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "production", required = false) String production,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgBaigeMek> dfUpBgBaigeMekQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfUpBgBaigeMekQueryWrapper.eq("process", process);
        }
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgBaigeMekQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgBaigeMekQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgBaigeMekQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgBaigeMekQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(state)) {
            dfUpBgBaigeMekQueryWrapper.eq("state", state);
        }
        if (StringUtils.isNotEmpty(production)) {
            dfUpBgBaigeMekQueryWrapper.eq("production", production);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgBaigeMekQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgBaigeMek> pageResult = dfUpBgBaigeMekService.page(
                new Page<>(page, limit),
                dfUpBgBaigeMekQueryWrapper
        );

        return R.ok(pageResult);
    }














}
