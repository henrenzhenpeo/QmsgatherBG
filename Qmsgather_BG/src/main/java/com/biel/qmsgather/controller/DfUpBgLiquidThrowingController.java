package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgLiquidThrowing;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.service.DfUpBgLiquidThrowingService;
import com.biel.qmsgather.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bg/dfUpBgLiquidThrowing")
@CrossOrigin
@Api(tags = "bg 液抛接口")
public class DfUpBgLiquidThrowingController {

    @Autowired
    private DfUpBgLiquidThrowingService dfUpBgLiquidThrowingService;


    @PostMapping("/upload")
    @ApiOperation(value = "bg 液抛接口上传")
    public Result uploadDfUpBgLiquidThrowing(@RequestBody List<DfUpBgLiquidThrowing> dfUpBgLiquidThrowingList){

        String newBatchId = dfUpBgLiquidThrowingService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgLiquidThrowing dfUpBgLiquidThrowing : dfUpBgLiquidThrowingList) {
            dfUpBgLiquidThrowing.setBatchId(dfUpBgLiquidThrowing.getProcess()+"-"+newBatchId);
        }

        boolean b = dfUpBgLiquidThrowingService.saveBatch(dfUpBgLiquidThrowingList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"bg 液抛接口上传成功");
        }

        return new Result(500,"bg 液抛接口上传失败");
    }


    @GetMapping("/findDfUpBgLiquidThrowing")
    @ApiOperation(value = "bg 液抛查询接口")
    public R findDfUpBgLiquidThrowing(
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
        QueryWrapper<DfUpBgLiquidThrowing> dfUpBgLiquidThrowingQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfUpBgLiquidThrowingQueryWrapper.eq("process", process);
        }
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgLiquidThrowingQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgLiquidThrowingQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgLiquidThrowingQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgLiquidThrowingQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgLiquidThrowingQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgLiquidThrowing> pageResult = dfUpBgLiquidThrowingService.page(
                new Page<>(page, limit),
                dfUpBgLiquidThrowingQueryWrapper
        );

        return R.ok(pageResult);
    }


}
