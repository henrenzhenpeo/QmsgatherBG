package com.biel.qmsgather.controller;


import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.biel.qmsgather.service.DfUpBgInkLandHeightService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgInkLandHeight")
@CrossOrigin
@Api(tags = "总厚+积油高度接口")
public class DfUpBgInkLandHeightController {

    @Autowired
    private DfUpBgInkLandHeightService dfUpBgInkLandHeightService;

    @PostMapping("/upload")
    @ApiOperation(value = "总厚+积油高度接口上传")
    public Result uploadDfUpBgInkDensity(@RequestBody List<DfUpBgInkLandHeight> dfUpBgInkLandHeightList){

        String newBatchId = dfUpBgInkLandHeightService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgInkLandHeight dfUpBgInkLandHeight : dfUpBgInkLandHeightList) {
            dfUpBgInkLandHeight.setBatchId(newBatchId);
        }

        boolean b = dfUpBgInkLandHeightService.saveBatch(dfUpBgInkLandHeightList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"总厚+积油高度接口上传成功");
        }

        return new Result(500,"总厚+积油高度接口上传失败");
    }


}
