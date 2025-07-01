package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgGrindingBottom;
import com.biel.qmsgather.service.DfUpBgGrindingBottomService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgGrindingBottom")
@CrossOrigin
@Api(tags = "BG 磨底接口")
public class DfUpBgGrindingBottomController {

    @Autowired
    private DfUpBgGrindingBottomService dfUpBgGrindingBottomService;


    @PostMapping("/upload")
    @ApiOperation(value = "BG 磨底接口上传")
    public Result uploadDfUpBgGrindingBottom(@RequestBody List<DfUpBgGrindingBottom> dfUpBgGrindingBottomList) {
        String newBatchId = dfUpBgGrindingBottomService.getMaxBatchId();
        System.out.println("newBatchId" + newBatchId);

        for (DfUpBgGrindingBottom dfUpBgGrindingBottom : dfUpBgGrindingBottomList) {
            dfUpBgGrindingBottom.setBatchId(dfUpBgGrindingBottom.getProcess()+ "-" + newBatchId);
        }

        boolean b = dfUpBgGrindingBottomService.saveBatch(dfUpBgGrindingBottomList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200, "BG 磨底接口上传成功");
        }

        return new Result(500, "BG 磨底接口上传失败");
    }


    @GetMapping("/findDfUpBgGrindingBottom")
    @ApiOperation(value = "BG 磨底查询接口")
    public R findDfUpBgGrindingBottom(
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
        QueryWrapper<DfUpBgGrindingBottom> dfUpBgGrindingBottomQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfUpBgGrindingBottomQueryWrapper.eq("process", process);
        }
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgGrindingBottomQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgGrindingBottomQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgGrindingBottomQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgGrindingBottomQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgGrindingBottomQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgGrindingBottom> pageResult = dfUpBgGrindingBottomService.page(
                new Page<>(page, limit),
                dfUpBgGrindingBottomQueryWrapper
        );

        return R.ok(pageResult);
    }



}
