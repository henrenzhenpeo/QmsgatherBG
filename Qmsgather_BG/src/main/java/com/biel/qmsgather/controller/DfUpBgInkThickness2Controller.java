package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgInkThickness2;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;

import com.biel.qmsgather.service.DfUpBgInkLandHeightService;
import com.biel.qmsgather.service.DfUpBgInkThickness2Service;
import com.biel.qmsgather.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgInkThickness2")
@CrossOrigin
@Api(tags = "油墨厚度接口2")
public class DfUpBgInkThickness2Controller {


    @Autowired
    private DfUpBgInkThickness2Service dfUpBgInkThickness2Service;

    @Autowired
    private DfUpBgInkLandHeightService dfUpBgInkLandHeightService;

    // @Autowired
    // private DfUpBgInkDensityService dfUpBgInkDensityService;




    @PostMapping("/upload")
    @ApiOperation(value = "油墨厚度上传接口")
    public Result uploadDfUpBgInkThickness(@RequestBody List<DfUpBgInkThickness2> dfUpBgInkThickness2List){
        String newBatchId = dfUpBgInkThickness2Service.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgInkThickness2 dfUpBgInkThickness2 : dfUpBgInkThickness2List) {
            dfUpBgInkThickness2.setBatchId(newBatchId);
        }

        boolean b = dfUpBgInkThickness2Service.saveBatch(dfUpBgInkThickness2List);
        if (b) {
            return new Result(200,"油墨厚度接口上传成功");
        }

        return new Result(500,"油墨厚度接口上传失败");
    }


    @GetMapping("/findDfUpBgInkThickness2")
    @ApiOperation(value = "油墨厚度查询接口")
    public R findDfUpBgInkThickness(
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgInkThickness2> dfUpBgInkThicknessQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgInkThicknessQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgInkThicknessQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgInkThicknessQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgInkThicknessQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgInkThicknessQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgInkThickness2> pageResult = dfUpBgInkThickness2Service.page(
                new Page<>(page, limit),
                dfUpBgInkThicknessQueryWrapper
        );

        return R.ok(pageResult);
    }






    @PostMapping("/uploadExcelWithJson")
    @ApiOperation(value = "bg 水滴接口上传Excel和JSON数据")
    public Result uploadExcelWithJson(@RequestParam("file") MultipartFile file,
                                      @RequestParam("jsonData") String jsonData) {
        try {
            // 解析JSON数据
            DfUpBgExcelDto baseInfo = new ObjectMapper().readValue(jsonData, DfUpBgExcelDto.class);

            // 调用服务处理Excel和JSON数据
             boolean result = dfUpBgInkThickness2Service.saveExcelWithJson(file, baseInfo);
             boolean result1 = dfUpBgInkLandHeightService.saveExcelWithJson(file, baseInfo);

            // boolean result2 = dfUpBgInkDensityService.saveExcelWithJson(file, baseInfo);

            if (result1 || result) {
                return new Result(200, "bg 油墨厚度接口上传成功");
            } else {
                return new Result(500, "bg 油墨厚度接口上传上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500, "bg 液抛Excel和JSON数据上传失败: " + e.getMessage());
        }
    }















}
