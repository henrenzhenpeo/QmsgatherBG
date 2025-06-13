package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfOrtGrAininessDetail;
import com.biel.qmsgather.domain.DfOrtGrAininessResult;
import com.biel.qmsgather.domain.DfOrtInkThickness;
import com.biel.qmsgather.service.DfOrtInkThicknessService;
import com.biel.qmsgather.util.DateUtil;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bg/dfOrtInkThickness")
@CrossOrigin
@Api(tags = "BG ort油墨厚度接口")
public class DfOrtInkThicknessController {

    @Autowired
    private DfOrtInkThicknessService ortInkThicknessService;

    @PostMapping("/uploadDfOrtInkThickness")
    @ApiOperation(value = "bg ort油墨厚度接口")
    public Result uploadDfOrtInkThickness(@RequestBody List<DfOrtInkThickness> dfOrtInkThicknessList){
        for (DfOrtInkThickness dfOrtInkThickness : dfOrtInkThicknessList) {
            String batchFromDate = DateUtil.getBatchFromDate(dfOrtInkThickness.getTestTime());
            dfOrtInkThickness.setBatch(batchFromDate);
        }


        boolean save = ortInkThicknessService.saveBatch(dfOrtInkThicknessList);
        if (save){
            return new Result(200, "bg ort油墨厚度上传成功");
        }
        return new Result(500, "bg ort油墨厚度上传失败");
    }

}
