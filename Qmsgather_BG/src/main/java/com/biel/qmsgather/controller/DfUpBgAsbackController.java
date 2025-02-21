package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgAsback;
import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.domain.DfUpBgZhongkaoBack;
import com.biel.qmsgather.domain.dto.DfUpBgAsbackDto;
import com.biel.qmsgather.domain.dto.DfUpBgZhongkaoBackDto;
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

    @PostMapping("/findDfUpBgAsback")
    @ApiOperation(value = "BG AS后查询接口")
    public R findDfUpBgZhongkaoBack(@RequestBody DfUpBgAsbackDto dfUpBgAsbackDto) {
        QueryWrapper<DfUpBgAsback> dfUpBgAsbackQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dfUpBgAsbackDto.getProcess())) {
            dfUpBgAsbackQueryWrapper.eq("process", dfUpBgAsbackDto.getProcess());
        }
        if (StringUtils.isNotEmpty(dfUpBgAsbackDto.getFactory())) {
            dfUpBgAsbackQueryWrapper.eq("factory", dfUpBgAsbackDto.getFactory());
        }
        if (StringUtils.isNotEmpty(dfUpBgAsbackDto.getStage())) {
            dfUpBgAsbackQueryWrapper.eq("stage", dfUpBgAsbackDto.getStage());
        }
        if (StringUtils.isNotEmpty(dfUpBgAsbackDto.getProject())) {
            dfUpBgAsbackQueryWrapper.eq("project", dfUpBgAsbackDto.getProject());
        }
        if (StringUtils.isNotEmpty(dfUpBgAsbackDto.getColor())) {
            dfUpBgAsbackQueryWrapper.eq("color", dfUpBgAsbackDto.getColor());
        }
        dfUpBgAsbackQueryWrapper.between("test_date",dfUpBgAsbackDto.getStartTestDate(),dfUpBgAsbackDto.getEndTestDate());

        IPage<DfUpBgAsback> page = dfUpBgAsbackService.page(new Page<>(dfUpBgAsbackDto.getPageIndex(), dfUpBgAsbackDto.getPageSize()), dfUpBgAsbackQueryWrapper);
        return R.ok(page);
    }



}
