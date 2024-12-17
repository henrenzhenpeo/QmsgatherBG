package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfUpBgAsback;
import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.service.DfUpBgAsbackService;
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
@RequestMapping("/bg/dfUpBgAsback")
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

}
