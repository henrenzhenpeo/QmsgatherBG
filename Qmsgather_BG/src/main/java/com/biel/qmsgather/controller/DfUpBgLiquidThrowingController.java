package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.biel.qmsgather.domain.DfUpBgLiquidThrowing;
import com.biel.qmsgather.domain.DfUpBgSandBlast;
import com.biel.qmsgather.domain.dto.DfUpBgLiquidThrowingDto;
import com.biel.qmsgather.domain.dto.DfUpBgSandBlastDto;
import com.biel.qmsgather.service.DfUpBgLiquidThrowingService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping("/findDfUpBgLiquidThrowing")
    @ApiOperation(value = "bg 液抛查询接口")
    public R findDfUpBgSandBlast(@RequestBody DfUpBgLiquidThrowingDto dfUpBgLiquidThrowingDto) {
        QueryWrapper<DfUpBgLiquidThrowing> dfUpBgLiquidThrowingQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dfUpBgLiquidThrowingDto.getProcess())) {
            dfUpBgLiquidThrowingQueryWrapper.eq("process", dfUpBgLiquidThrowingDto.getProcess());
        }
        if (StringUtils.isNotEmpty(dfUpBgLiquidThrowingDto.getFactory())) {
            dfUpBgLiquidThrowingQueryWrapper.eq("factory", dfUpBgLiquidThrowingDto.getFactory());
        }
        if (StringUtils.isNotEmpty(dfUpBgLiquidThrowingDto.getStage())) {
            dfUpBgLiquidThrowingQueryWrapper.eq("stage", dfUpBgLiquidThrowingDto.getStage());
        }
        if (StringUtils.isNotEmpty(dfUpBgLiquidThrowingDto.getProject())) {
            dfUpBgLiquidThrowingQueryWrapper.eq("project", dfUpBgLiquidThrowingDto.getProject());
        }
        if (StringUtils.isNotEmpty(dfUpBgLiquidThrowingDto.getColor())) {
            dfUpBgLiquidThrowingQueryWrapper.eq("color", dfUpBgLiquidThrowingDto.getColor());
        }
        dfUpBgLiquidThrowingQueryWrapper.between("test_date",dfUpBgLiquidThrowingDto.getStartTestDate(),dfUpBgLiquidThrowingDto.getEndTestDate());

        IPage<DfUpBgLiquidThrowing> page = dfUpBgLiquidThrowingService.page(new Page<>(dfUpBgLiquidThrowingDto.getPageIndex(), dfUpBgLiquidThrowingDto.getPageSize()), dfUpBgLiquidThrowingQueryWrapper);
        return R.ok(page);
    }

}
