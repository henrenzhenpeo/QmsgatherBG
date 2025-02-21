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


    @PostMapping("/findDfUpBgInkDensity")
    @ApiOperation(value = "OD密度查询接口")
    public R findDfUpBgInkDensity(@RequestBody DfUpBgInkDensityDto dfUpBgInkDensityDto) {
        QueryWrapper<DfUpBgInkDensity> dfUpBgInkDensityQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dfUpBgInkDensityDto.getFactory())) {
            dfUpBgInkDensityQueryWrapper.eq("factory", dfUpBgInkDensityDto.getFactory());
        }
        if (StringUtils.isNotEmpty(dfUpBgInkDensityDto.getStage())) {
            dfUpBgInkDensityQueryWrapper.eq("stage", dfUpBgInkDensityDto.getStage());
        }
        if (StringUtils.isNotEmpty(dfUpBgInkDensityDto.getProject())) {
            dfUpBgInkDensityQueryWrapper.eq("project", dfUpBgInkDensityDto.getProject());
        }
        if (StringUtils.isNotEmpty(dfUpBgInkDensityDto.getColor())) {
            dfUpBgInkDensityQueryWrapper.eq("color", dfUpBgInkDensityDto.getColor());
        }
        dfUpBgInkDensityQueryWrapper.between("test_date",dfUpBgInkDensityDto.getStartTestDate(),dfUpBgInkDensityDto.getEndTestDate());

        IPage<DfUpBgInkDensity> page = dfUpBgInkDensityService.page(new Page<>(dfUpBgInkDensityDto.getPageIndex(), dfUpBgInkDensityDto.getPageSize()), dfUpBgInkDensityQueryWrapper);
        return R.ok(page);
    }

}
