package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgAsback;
import com.biel.qmsgather.domain.DfUpBgGrindingBottom;
import com.biel.qmsgather.domain.DfUpBgZhongkaoBack;
import com.biel.qmsgather.domain.dto.DfUpBgGrindingBottomDto;
import com.biel.qmsgather.domain.dto.DfUpBgZhongkaoBackDto;
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


    @PostMapping("/findDfUpBgGrindingBottom")
    @ApiOperation(value = "BG 磨底查询接口")
    public R findDfUpBgGrindingBottom(@RequestBody DfUpBgGrindingBottomDto dfUpBgGrindingBottomDto) {
        QueryWrapper<DfUpBgGrindingBottom> dfUpBgGrindingBottomQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dfUpBgGrindingBottomDto.getProcess())) {
            dfUpBgGrindingBottomQueryWrapper.eq("process", dfUpBgGrindingBottomDto.getProcess());
        }
        if (StringUtils.isNotEmpty(dfUpBgGrindingBottomDto.getFactory())) {
            dfUpBgGrindingBottomQueryWrapper.eq("factory", dfUpBgGrindingBottomDto.getFactory());
        }
        if (StringUtils.isNotEmpty(dfUpBgGrindingBottomDto.getStage())) {
            dfUpBgGrindingBottomQueryWrapper.eq("stage", dfUpBgGrindingBottomDto.getStage());
        }
        if (StringUtils.isNotEmpty(dfUpBgGrindingBottomDto.getProject())) {
            dfUpBgGrindingBottomQueryWrapper.eq("project", dfUpBgGrindingBottomDto.getProject());
        }
        if (StringUtils.isNotEmpty(dfUpBgGrindingBottomDto.getColor())) {
            dfUpBgGrindingBottomQueryWrapper.eq("color", dfUpBgGrindingBottomDto.getColor());
        }
        dfUpBgGrindingBottomQueryWrapper.between("test_date",dfUpBgGrindingBottomDto.getStartTestDate(),dfUpBgGrindingBottomDto.getEndTestDate());

        IPage<DfUpBgGrindingBottom> page = dfUpBgGrindingBottomService.page(new Page<>(dfUpBgGrindingBottomDto.getPageIndex(), dfUpBgGrindingBottomDto.getPageSize()), dfUpBgGrindingBottomQueryWrapper);
        return R.ok(page);
    }


}
