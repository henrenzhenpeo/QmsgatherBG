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


    @PostMapping("/findDfUpBgInkLandHeight")
    @ApiOperation(value = "总厚+积油高度查询接口")
    public R findDfUpBgInkLandHeight(@RequestBody DfUpBgInkLandHeightDto dfUpBgInkLandHeightDto) {
        QueryWrapper<DfUpBgInkLandHeight> dfUpBgInkLandHeightQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dfUpBgInkLandHeightDto.getFactory())) {
            dfUpBgInkLandHeightQueryWrapper.eq("factory", dfUpBgInkLandHeightDto.getFactory());
        }
        if (StringUtils.isNotEmpty(dfUpBgInkLandHeightDto.getStage())) {
            dfUpBgInkLandHeightQueryWrapper.eq("stage", dfUpBgInkLandHeightDto.getStage());
        }
        if (StringUtils.isNotEmpty(dfUpBgInkLandHeightDto.getProject())) {
            dfUpBgInkLandHeightQueryWrapper.eq("project", dfUpBgInkLandHeightDto.getProject());
        }
        if (StringUtils.isNotEmpty(dfUpBgInkLandHeightDto.getColor())) {
            dfUpBgInkLandHeightQueryWrapper.eq("color", dfUpBgInkLandHeightDto.getColor());
        }
        dfUpBgInkLandHeightQueryWrapper.between("test_date",dfUpBgInkLandHeightDto.getStartTestDate(),dfUpBgInkLandHeightDto.getEndTestDate());

        IPage<DfUpBgInkLandHeight> page = dfUpBgInkLandHeightService.page(new Page<>(dfUpBgInkLandHeightDto.getPageIndex(), dfUpBgInkLandHeightDto.getPageSize()), dfUpBgInkLandHeightQueryWrapper);
        return R.ok(page);
    }

}
