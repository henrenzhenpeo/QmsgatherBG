package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgAsback;
import com.biel.qmsgather.service.DfUpBgAsbackService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgAsback")
@CrossOrigin
@Api(tags = "BG AS后接口")
public class DfUpBgAsbackController {

    @Autowired
    private DfUpBgAsbackService dfUpBgAsbackService;

    @PostMapping("/upload")
    @ApiOperation(value = "BG AS后接口上传")
    public Result uploadDfUpBgAsback(@RequestBody List<DfUpBgAsback> dfUpBgAsbackList) {
        String newBatchId = dfUpBgAsbackService.getMaxBatchId();
        System.out.println("newBatchId" + newBatchId);

        for (DfUpBgAsback dfUpBgAsback : dfUpBgAsbackList) {
            dfUpBgAsback.setBatchId(dfUpBgAsback.getProcess() + "-" + newBatchId);
        }

        boolean b = dfUpBgAsbackService.saveBatch(dfUpBgAsbackList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200, "BG AS后接口上传成功");
        }

        return new Result(500, "BG AS后接口上传失败");
    }

    @GetMapping("/findDfUpBgAsback")
    @ApiOperation(value = "BG AS后查询接口")
    public R findDfUpBgAsback(
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
        QueryWrapper<DfUpBgAsback> dfUpBgAsbackQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfUpBgAsbackQueryWrapper.eq("process", process);
        }
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgAsbackQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgAsbackQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgAsbackQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgAsbackQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgAsbackQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgAsback> pageResult = dfUpBgAsbackService.page(
                new Page<>(page, limit),
                dfUpBgAsbackQueryWrapper
        );

        return R.ok(pageResult);
    }



}
