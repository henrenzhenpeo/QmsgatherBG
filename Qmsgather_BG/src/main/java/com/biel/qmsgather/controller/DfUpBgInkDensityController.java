package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.biel.qmsgather.domain.DfUpBgInkThickness;
import com.biel.qmsgather.domain.dto.DfUpBgInkDensityDto;
import com.biel.qmsgather.domain.dto.DfUpBgInkThicknessDto;
import com.biel.qmsgather.service.DfUpBgInkDensityService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgInkDensity")
@CrossOrigin
@Api(tags = "OD密度接口")
public class DfUpBgInkDensityController {


    @Autowired
    private DfUpBgInkDensityService dfUpBgInkDensityService;

    @PostMapping("/upload")
    @ApiOperation(value = "OD密度接口接口上传")
    public Result uploadDfUpBgInkDensity(@RequestBody List<DfUpBgInkDensity> dfUpBgInkDensityList){

        String newBatchId = dfUpBgInkDensityService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgInkDensity dfUpBgInkDensity : dfUpBgInkDensityList) {
            dfUpBgInkDensity.setBatchId(newBatchId);
        }

        boolean b = dfUpBgInkDensityService.saveBatch(dfUpBgInkDensityList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"OD密度接口上传成功");
        }

        return new Result(500,"OD密度接口上传失败");
    }


    @GetMapping("/findDfUpBgInkDensity")
    @ApiOperation(value = "OD密度查询接口")
    public R findDfUpBgInkDensity(
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgInkDensity> dfUpBgInkDensityQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgInkDensityQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgInkDensityQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgInkDensityQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgInkDensityQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgInkDensityQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgInkDensity> pageResult = dfUpBgInkDensityService.page(
                new Page<>(page, limit),
                dfUpBgInkDensityQueryWrapper
        );

        return R.ok(pageResult);
    }


}
