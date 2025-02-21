package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.domain.DfUpBgGrindingBottom;
import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.biel.qmsgather.domain.dto.DfUpBgDripDto;
import com.biel.qmsgather.domain.dto.DfUpBgGrindingBottomDto;
import com.biel.qmsgather.service.DfUpBgDripService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgDrip")
@CrossOrigin
@Api(tags = "水滴角接口")
public class DfUpBgDripController {

    @Autowired
    private DfUpBgDripService dfUpBgDripService;

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


    @PostMapping("/findDfUpBgDrip")
    @ApiOperation(value = "水滴角查询接口")
    public R findDfUpBgDrip(@RequestBody DfUpBgDripDto dfUpBgDripDto) {
        QueryWrapper<DfUpBgDrip> dfUpBgDripQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dfUpBgDripDto.getProcess())) {
            dfUpBgDripQueryWrapper.eq("process", dfUpBgDripDto.getProcess());
        }
        if (StringUtils.isNotEmpty(dfUpBgDripDto.getFactory())) {
            dfUpBgDripQueryWrapper.eq("factory", dfUpBgDripDto.getFactory());
        }
        if (StringUtils.isNotEmpty(dfUpBgDripDto.getStage())) {
            dfUpBgDripQueryWrapper.eq("stage", dfUpBgDripDto.getStage());
        }
        if (StringUtils.isNotEmpty(dfUpBgDripDto.getProject())) {
            dfUpBgDripQueryWrapper.eq("project", dfUpBgDripDto.getProject());
        }
        if (StringUtils.isNotEmpty(dfUpBgDripDto.getColor())) {
            dfUpBgDripQueryWrapper.eq("color", dfUpBgDripDto.getColor());
        }
        dfUpBgDripQueryWrapper.between("test_date",dfUpBgDripDto.getStartTestDate(),dfUpBgDripDto.getEndTestDate());

        IPage<DfUpBgDrip> page = dfUpBgDripService.page(new Page<>(dfUpBgDripDto.getPageIndex(), dfUpBgDripDto.getPageSize()), dfUpBgDripQueryWrapper);
        return R.ok(page);
    }

}
