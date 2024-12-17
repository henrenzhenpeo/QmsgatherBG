package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfUpBgInkThickness;
import com.biel.qmsgather.domain.DfUpBgInkThicknessChild;
import com.biel.qmsgather.domain.DfUpBgLiquidThrowing;
import com.biel.qmsgather.service.DfUpBgInkThicknessChildService;
import com.biel.qmsgather.service.DfUpBgInkThicknessService;
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
@RequestMapping("/bg/dfUpBgInkThickness")
@Api(tags = "油墨厚度接口")
public class DfUpBgInkThicknessController {

    @Autowired
    private DfUpBgInkThicknessService dfUpBgInkThicknessService;

    @Autowired
    private DfUpBgInkThicknessChildService dfUpBgInkThicknessChildService;

    @PostMapping("/upload")
    @ApiOperation(value = "油墨厚度上传接口")
    public Result uploadDfUpBgInkThickness(@RequestBody DfUpBgInkThickness dfUpBgInkThicknesses){
        String newBatchId = dfUpBgInkThicknessService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);
        if (dfUpBgInkThicknesses!=null) {
            dfUpBgInkThicknesses.setBatchId(newBatchId);
            for (DfUpBgInkThicknessChild dfUpBgInkThicknessChild : dfUpBgInkThicknesses.getDfUpBgInkThicknessChildList()) {
                dfUpBgInkThicknessChild.setBatchId(newBatchId);
            }
            boolean b = dfUpBgInkThicknessService.save(dfUpBgInkThicknesses);
            if (b) {
                boolean saveBatch = dfUpBgInkThicknessChildService.saveBatch(dfUpBgInkThicknesses.getDfUpBgInkThicknessChildList());
                if (saveBatch) {
                    return new Result(200,"油墨厚度接口上传成功");
                }
            }
            return new Result(500,"油墨厚度接口上传失败");
        }
        return new Result(500,"请上传油墨厚度接口值");
    }

}
