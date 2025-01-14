package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.biel.qmsgather.domain.DfUpBgSandBlast;
import com.biel.qmsgather.service.DfUpBgSandBlastService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgSandBlast")
@CrossOrigin
@Api(tags = "bg喷砂接口")
public class DfUpBgSandBlastController {


    @Autowired
    private DfUpBgSandBlastService dfUpBgInkLandHeightList;

    @PostMapping("/upload")
    @ApiOperation(value = "bg喷砂接口上传")
    public Result uploadDfUpBgSandBlast(@RequestBody List<DfUpBgSandBlast> dfUpBgSandBlastList){

        String newBatchId = dfUpBgInkLandHeightList.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgSandBlast dfUpBgSandBlast : dfUpBgSandBlastList) {
            dfUpBgSandBlast.setBatchId(dfUpBgSandBlast.getProcess()+"-"+newBatchId);
        }

        boolean b = dfUpBgInkLandHeightList.saveBatch(dfUpBgSandBlastList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"bg喷砂接口上传成功");
        }

        return new Result(500,"bg喷砂接口上传失败");
    }
}
