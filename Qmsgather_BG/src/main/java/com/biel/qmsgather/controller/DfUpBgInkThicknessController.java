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


    @GetMapping("/findDfUpBgInkThickness")
    @ApiOperation(value = "油墨厚度查询接口")
    public R findDfUpBgInkThickness(
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgInkThickness> dfUpBgInkThicknessQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgInkThicknessQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgInkThicknessQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgInkThicknessQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgInkThicknessQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgInkThicknessQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgInkThickness> pageResult = dfUpBgInkThicknessService.page(
                new Page<>(page, limit),
                dfUpBgInkThicknessQueryWrapper
        );

        return R.ok(pageResult);
    }




}
