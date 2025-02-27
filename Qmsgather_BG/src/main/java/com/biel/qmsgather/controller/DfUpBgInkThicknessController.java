package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgInkThickness;
import com.biel.qmsgather.domain.DfUpBgInkThicknessChild;
import com.biel.qmsgather.domain.DfUpBgLiquidThrowing;
import com.biel.qmsgather.domain.dto.DfUpBgInkThicknessDto;
import com.biel.qmsgather.service.DfUpBgInkThicknessChildService;
import com.biel.qmsgather.service.DfUpBgInkThicknessService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgInkThickness")
@CrossOrigin
@Api(tags = "油墨厚度接口")
public class DfUpBgInkThicknessController {

    @Autowired
    private DfUpBgInkThicknessService dfUpBgInkThicknessService;

    @Autowired
    private DfUpBgInkThicknessChildService dfUpBgInkThicknessChildService;

    @PostMapping("/upload")
    @ApiOperation(value = "油墨厚度上传接口")
    public Result uploadDfUpBgInkThickness(@RequestBody DfUpBgInkThickness dfUpBgInkThicknesses){
        String newBatchId = dfUpBgInkThicknessService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);
        if (dfUpBgInkThicknesses!=null) {
            dfUpBgInkThicknesses.setBatchId(newBatchId);
            for (DfUpBgInkThicknessChild dfUpBgInkThicknessChild : dfUpBgInkThicknesses.getDfUpBgInkThicknessChildList()) {
                dfUpBgInkThicknessChild.setBatchId(newBatchId);
            }
            boolean b = dfUpBgInkThicknessService.save(dfUpBgInkThicknesses);
            if (b) {
                boolean saveBatch = dfUpBgInkThicknessChildService.saveBatch(dfUpBgInkThicknesses.getDfUpBgInkThicknessChildList());
                if (saveBatch) {
                    return new Result(200,"油墨厚度接口上传成功");
                }
            }
            return new Result(500,"油墨厚度接口上传失败");
        }
        return new Result(500,"请上传油墨厚度接口值");
    }


    @PostMapping("/findDfUpBgInkThickness")
    @ApiOperation(value = "油墨厚度查询接口")
    public R findDfUpBgInkThickness(@RequestBody DfUpBgInkThicknessDto dfUpBgInkThicknessDto) {
        QueryWrapper<DfUpBgInkThickness> dfUpBgInkThicknessQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dfUpBgInkThicknessDto.getFactory())) {
            dfUpBgInkThicknessQueryWrapper.eq("factory", dfUpBgInkThicknessDto.getFactory());
        }
        if (StringUtils.isNotEmpty(dfUpBgInkThicknessDto.getStage())) {
            dfUpBgInkThicknessQueryWrapper.eq("stage", dfUpBgInkThicknessDto.getStage());
        }
        if (StringUtils.isNotEmpty(dfUpBgInkThicknessDto.getProject())) {
            dfUpBgInkThicknessQueryWrapper.eq("project", dfUpBgInkThicknessDto.getProject());
        }
        if (StringUtils.isNotEmpty(dfUpBgInkThicknessDto.getColor())) {
            dfUpBgInkThicknessQueryWrapper.eq("color", dfUpBgInkThicknessDto.getColor());
        }
        dfUpBgInkThicknessQueryWrapper.between("test_date",dfUpBgInkThicknessDto.getStartTestDate(),dfUpBgInkThicknessDto.getEndTestDate());

        IPage<DfUpBgInkThickness> page = dfUpBgInkThicknessService.page(new Page<>(dfUpBgInkThicknessDto.getPageIndex(), dfUpBgInkThicknessDto.getPageSize()), dfUpBgInkThicknessQueryWrapper);
        return R.ok(page);
    }


















}
