package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.biel.qmsgather.domain.DfUpBgSandBlast;
import com.biel.qmsgather.domain.DfUpBgZhongkaoBack;
import com.biel.qmsgather.domain.dto.DfUpBgSandBlastDto;
import com.biel.qmsgather.domain.dto.DfUpBgZhongkaoBackDto;
import com.biel.qmsgather.service.DfUpBgZhongkaoBackService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgZhongkaoBack")
@CrossOrigin
@Api(tags = "bg终烤后接口")
public class DfUpBgZhongkaoBackController {

    @Autowired
    private DfUpBgZhongkaoBackService dfUpBgZhongkaoBackService;

    @PostMapping("/upload")
    @ApiOperation(value = "bg终烤后接口上传")
    public Result uploadDfUpBgZhongkaoBack(@RequestBody List<DfUpBgZhongkaoBack> dfUpBgZhongkaoBackList){

        String newBatchId = dfUpBgZhongkaoBackService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgZhongkaoBack dfUpBgZhongkaoBack : dfUpBgZhongkaoBackList) {
            dfUpBgZhongkaoBack.setBatchId(dfUpBgZhongkaoBack.getProcess()+"-"+newBatchId);
        }

        boolean b = dfUpBgZhongkaoBackService.saveBatch(dfUpBgZhongkaoBackList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"bg终烤后接口上传成功");
        }

        return new Result(500,"bg终烤后接口上传失败");
    }


    @GetMapping("/findDfUpBgZhongkaoBack")
    @ApiOperation(value = "bg终烤后查询接口")
    public R findDfUpBgZhongkaoBack(
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
        QueryWrapper<DfUpBgZhongkaoBack> dfUpBgZhongkaoBackQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfUpBgZhongkaoBackQueryWrapper.eq("process", process);
        }
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgZhongkaoBackQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgZhongkaoBackQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgZhongkaoBackQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgZhongkaoBackQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgZhongkaoBackQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgZhongkaoBack> pageResult = dfUpBgZhongkaoBackService.page(
                new Page<>(page, limit),
                dfUpBgZhongkaoBackQueryWrapper
        );

        return R.ok(pageResult);
    }

}
