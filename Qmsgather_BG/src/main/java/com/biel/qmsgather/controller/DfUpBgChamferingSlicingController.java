package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgChamferingSlicing;
import com.biel.qmsgather.domain.DfUpBgDayinpen;
import com.biel.qmsgather.service.DfUpBgChamferingSlicingService;
import com.biel.qmsgather.util.Result;
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

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgChamferingSlicing")
@CrossOrigin
@Api(tags = "BG 倒角切片")
public class DfUpBgChamferingSlicingController {

    @Autowired
    private DfUpBgChamferingSlicingService dfUpBgChamferingSlicingService;

    @PostMapping("/upload")
    @ApiOperation(value = "bg 倒角切片接口上传")
    public Result uploadDfUpBgChamferingSlicing(@RequestBody List<DfUpBgChamferingSlicing> dfUpBgChamferingSlicingList){

        String newBatchId = dfUpBgChamferingSlicingService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgChamferingSlicing dfUpBgChamferingSlicing : dfUpBgChamferingSlicingList) {
            dfUpBgChamferingSlicing.setBatchId(dfUpBgChamferingSlicing.getProject()+"-"+newBatchId);
        }

        boolean b = dfUpBgChamferingSlicingService.saveBatch(dfUpBgChamferingSlicingList);
        if (b) {
            return new Result(200,"bg 倒角切片接口上传成功");
        }

        return new Result(500,"bg 倒角切片接口上传失败");
    }

    @GetMapping("/findDfUpBgChamferingSlicing")
    @ApiOperation(value = "bg倒角切片查询接口")
    public R findDfUpBgChamferingSlicing(
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgChamferingSlicing> dfUpBgChamferingSlicingQueryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgChamferingSlicingQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgChamferingSlicingQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgChamferingSlicingQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgChamferingSlicingQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgChamferingSlicingQueryWrapper.between("date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgChamferingSlicing> pageResult = dfUpBgChamferingSlicingService.page(
                new Page<>(page, limit),
                dfUpBgChamferingSlicingQueryWrapper
        );

        return R.ok(pageResult);
    }

}
