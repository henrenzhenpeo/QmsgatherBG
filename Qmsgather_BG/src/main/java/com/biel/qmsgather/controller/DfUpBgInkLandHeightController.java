package com.biel.qmsgather.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.biel.qmsgather.domain.dto.DfUpBgInkDensityDto;
import com.biel.qmsgather.domain.dto.DfUpBgInkLandHeightDto;
import com.biel.qmsgather.service.DfUpBgInkLandHeightService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgInkLandHeight")
@CrossOrigin
@Api(tags = "总厚+积油高度接口")
public class DfUpBgInkLandHeightController {

    @Autowired
    private DfUpBgInkLandHeightService dfUpBgInkLandHeightService;

    @PostMapping("/upload")
    @ApiOperation(value = "总厚+积油高度接口上传")
    public Result uploadDfUpBgInkDensity(@RequestBody List<DfUpBgInkLandHeight> dfUpBgInkLandHeightList){

        String newBatchId = dfUpBgInkLandHeightService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgInkLandHeight dfUpBgInkLandHeight : dfUpBgInkLandHeightList) {
            dfUpBgInkLandHeight.setBatchId(newBatchId);
        }

        boolean b = dfUpBgInkLandHeightService.saveBatch(dfUpBgInkLandHeightList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"总厚+积油高度接口上传成功");
        }

        return new Result(500,"总厚+积油高度接口上传失败");
    }


    @GetMapping("/findDfUpBgInkLandHeight")
    @ApiOperation(value = "总厚+积油高度查询接口")
    public R findDfUpBgInkLandHeight(
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        QueryWrapper<DfUpBgInkLandHeight> dfUpBgInkLandHeightQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgInkLandHeightQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgInkLandHeightQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgInkLandHeightQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(state)) {
            dfUpBgInkLandHeightQueryWrapper.eq("state", state);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgInkLandHeightQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgInkLandHeightQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 分页查询
        IPage<DfUpBgInkLandHeight> pageResult = dfUpBgInkLandHeightService.page(
                new Page<>(page, limit),
                dfUpBgInkLandHeightQueryWrapper
        );

        return R.ok(pageResult);
    }

}
