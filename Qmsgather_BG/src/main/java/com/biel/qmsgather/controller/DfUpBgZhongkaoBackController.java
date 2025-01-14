package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.biel.qmsgather.domain.DfUpBgZhongkaoBack;
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
}
