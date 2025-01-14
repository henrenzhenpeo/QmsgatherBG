package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.biel.qmsgather.service.DfUpBgDripService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgDrip")
@CrossOrigin
@Api(tags = "水滴角接口")
public class DfUpBgDripController {

    @Autowired
    private DfUpBgDripService dfUpBgDripService;

    @PostMapping("/upload")
    @ApiOperation(value = "水滴角接口上传")
    public Result uploadDfUpBgInkDensity(@RequestBody List<DfUpBgDrip> dfUpBgDripList){

        String newBatchId = dfUpBgDripService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgDrip dfUpBgDrip : dfUpBgDripList) {
            dfUpBgDrip.setBatchId(dfUpBgDrip.getProcess()+"-"+newBatchId);
        }

        boolean b = dfUpBgDripService.saveBatch(dfUpBgDripList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"水滴角接口上传成功");
        }

        return new Result(500,"水滴角接口上传失败");
    }

}
