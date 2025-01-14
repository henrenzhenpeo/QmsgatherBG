package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.biel.qmsgather.domain.DfUpBgLiquidThrowing;
import com.biel.qmsgather.service.DfUpBgLiquidThrowingService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgLiquidThrowing")
@CrossOrigin
@Api(tags = "bg 液抛接口")
public class DfUpBgLiquidThrowingController {

    @Autowired
    private DfUpBgLiquidThrowingService dfUpBgLiquidThrowingService;


    @PostMapping("/upload")
    @ApiOperation(value = "bg 液抛接口上传")
    public Result uploadDfUpBgLiquidThrowing(@RequestBody List<DfUpBgLiquidThrowing> dfUpBgLiquidThrowingList){

        String newBatchId = dfUpBgLiquidThrowingService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgLiquidThrowing dfUpBgLiquidThrowing : dfUpBgLiquidThrowingList) {
            dfUpBgLiquidThrowing.setBatchId(dfUpBgLiquidThrowing.getProcess()+"-"+newBatchId);
        }

        boolean b = dfUpBgLiquidThrowingService.saveBatch(dfUpBgLiquidThrowingList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"bg 液抛接口上传成功");
        }

        return new Result(500,"bg 液抛接口上传失败");
    }

}
