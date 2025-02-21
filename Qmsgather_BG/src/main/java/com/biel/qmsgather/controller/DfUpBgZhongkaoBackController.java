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


    @PostMapping("/findDfUpBgZhongkaoBack")
    @ApiOperation(value = "bg终烤后查询接口")
    public R findDfUpBgZhongkaoBack(@RequestBody DfUpBgZhongkaoBackDto dfUpBgZhongkaoBackDto) {
        QueryWrapper<DfUpBgZhongkaoBack> dfUpBgZhongkaoBackQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dfUpBgZhongkaoBackDto.getProcess())) {
            dfUpBgZhongkaoBackQueryWrapper.eq("process", dfUpBgZhongkaoBackDto.getProcess());
        }
        if (StringUtils.isNotEmpty(dfUpBgZhongkaoBackDto.getFactory())) {
            dfUpBgZhongkaoBackQueryWrapper.eq("factory", dfUpBgZhongkaoBackDto.getFactory());
        }
        if (StringUtils.isNotEmpty(dfUpBgZhongkaoBackDto.getStage())) {
            dfUpBgZhongkaoBackQueryWrapper.eq("stage", dfUpBgZhongkaoBackDto.getStage());
        }
        if (StringUtils.isNotEmpty(dfUpBgZhongkaoBackDto.getProject())) {
            dfUpBgZhongkaoBackQueryWrapper.eq("project", dfUpBgZhongkaoBackDto.getProject());
        }
        if (StringUtils.isNotEmpty(dfUpBgZhongkaoBackDto.getColor())) {
            dfUpBgZhongkaoBackQueryWrapper.eq("color", dfUpBgZhongkaoBackDto.getColor());
        }
        dfUpBgZhongkaoBackQueryWrapper.between("test_date",dfUpBgZhongkaoBackDto.getStartTestDate(),dfUpBgZhongkaoBackDto.getEndTestDate());

        IPage<DfUpBgZhongkaoBack> page = dfUpBgZhongkaoBackService.page(new Page<>(dfUpBgZhongkaoBackDto.getPageIndex(), dfUpBgZhongkaoBackDto.getPageSize()), dfUpBgZhongkaoBackQueryWrapper);
        return R.ok(page);
    }
}
