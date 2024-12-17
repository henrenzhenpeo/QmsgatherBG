package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfUpBgAsback;
import com.biel.qmsgather.domain.DfUpBgGrindingBottom;
import com.biel.qmsgather.service.DfUpBgGrindingBottomService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgGrindingBottom")
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


}
