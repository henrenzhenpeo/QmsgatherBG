package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfOrtOilInk;
import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.domain.DfUpBgGrindingBottom;
import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.biel.qmsgather.domain.dto.DfUpBgDripDto;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.domain.dto.DfUpBgGrindingBottomDto;
import com.biel.qmsgather.service.DfOrtOilInkService;
import com.biel.qmsgather.service.DfUpBgDripService;
import com.biel.qmsgather.util.DateUtil;
import com.biel.qmsgather.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgDrip")
@CrossOrigin
@Api(tags = "水滴角接口")
public class DfUpBgDripController {

    @Autowired
    private DfUpBgDripService dfUpBgDripService;

    @Autowired
    private DfOrtOilInkService dfOrtOilInkService;


    @PostMapping("/uploadDfOrtOilInkService")
    @ApiOperation(value = "水滴角接口上传")
    public Result uploadDfOrtOilInkService(@RequestBody List<DfOrtOilInk> dfOrtOilInkList){
        for (DfOrtOilInk dfOrtOilInk : dfOrtOilInkList) {
            System.out.println(dfOrtOilInk.getCheckTime());
            String batchFromDate = DateUtil.getBatchFromDate(dfOrtOilInk.getCheckTime());
            dfOrtOilInk.setBatch(batchFromDate);
            if (dfOrtOilInk.getDayOrNight().equals("白斑")) {
                dfOrtOilInk.setDayOrNight("A");
            }else if (dfOrtOilInk.getDayOrNight().equals("晚班")) {
                dfOrtOilInk.setDayOrNight("B");
            }
        }

        boolean b = dfOrtOilInkService.saveBatch(dfOrtOilInkList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"水滴角接口上传成功");
        }

        return new Result(500,"水滴角接口上传失败");
    }


    @PostMapping("/upload")
    @ApiOperation(value = "水滴角接口上传")
    public Result uploadDfUpBgInkDensity(@RequestBody List<DfUpBgDrip> dfUpBgDripList){

        String newBatchId = dfUpBgDripService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgDrip dfUpBgDrip : dfUpBgDripList) {
            dfUpBgDrip.setBatchId(dfUpBgDrip.getProcess()+"-"+newBatchId);
        }

        boolean b = dfUpBgDripService.saveBatch(dfUpBgDripList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"水滴角接口上传成功");
        }

        return new Result(500,"水滴角接口上传失败");
    }


    @GetMapping("/findDfUpBgDrip")
    @ApiOperation(value = "水滴角查询接口")
    public R findDfUpBgDrip(
            @RequestParam(value = "process", required = false) String process,
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgDrip> dfUpBgDripQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfUpBgDripQueryWrapper.eq("process", process);
        }
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgDripQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgDripQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgDripQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgDripQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgDripQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 分页查询
        IPage<DfUpBgDrip> pageResult = dfUpBgDripService.page(
                new Page<>(page, limit),
                dfUpBgDripQueryWrapper
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
            boolean result = dfUpBgDripService.saveExcelWithJson(file, baseInfo);



            if (result) {
                return new Result(200, "bg 水滴角接口上传成功");
            } else {
                return new Result(500, "bg 水滴角接口上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500, "bg 水滴角接口上传失败: " + e.getMessage());
        }
    }














}
