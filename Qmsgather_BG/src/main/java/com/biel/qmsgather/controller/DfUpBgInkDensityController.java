package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.biel.qmsgather.service.DfUpBgInkDensityService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgInkDensity")
@CrossOrigin
@Api(tags = "OD密度接口")
public class DfUpBgInkDensityController {


    @Autowired
    private DfUpBgInkDensityService dfUpBgInkDensityService;

    @PostMapping("/upload")
    @ApiOperation(value = "OD密度接口接口上传")
    public Result uploadDfUpBgInkDensity(@RequestBody List<DfUpBgInkDensity> dfUpBgInkDensityList){

        String newBatchId = dfUpBgInkDensityService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgInkDensity dfUpBgInkDensity : dfUpBgInkDensityList) {
            dfUpBgInkDensity.setBatchId(newBatchId);
        }

        boolean b = dfUpBgInkDensityService.saveBatch(dfUpBgInkDensityList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"OD密度接口上传成功");
        }

        return new Result(500,"OD密度接口上传失败");
    }

}
