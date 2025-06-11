package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfOrtGlossDetail;
import com.biel.qmsgather.domain.DfOrtGlossResult;
import com.biel.qmsgather.domain.DfOrtGrAininessDetail;
import com.biel.qmsgather.domain.DfOrtGrAininessResult;
import com.biel.qmsgather.service.DfOrtGrAininessDetailService;
import com.biel.qmsgather.service.DfOrtGrAininessResultService;
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

@RestController
@RequestMapping("/bg/dfOrtGrAininess")
@CrossOrigin
@Api(tags = "BG ort颗粒度接口")
public class DfOrtGrAininessController {

    @Autowired
    private DfOrtGrAininessResultService dfOrtGrAininessResultService;

    @Autowired
    private DfOrtGrAininessDetailService dfOrtGrAininessDetailService;

    @PostMapping("/uploadDfOrtGrAininess")
    @ApiOperation(value = "bg颗粒度上传接口")
    public Result uploadDfOrtGrAininess(@RequestBody DfOrtGrAininessResult dfOrtGrAininessResult){
        String batchFromDate = DateUtil.getBatchFromDate(dfOrtGrAininessResult.getTestTime());
        dfOrtGrAininessResult.setBatch(batchFromDate);
        for (DfOrtGrAininessDetail dfOrtGrAininessDetail : dfOrtGrAininessResult.getDfOrtGrAininessDetailList()) {
            dfOrtGrAininessDetail.setBatch(batchFromDate);
        }
        boolean save = dfOrtGrAininessResultService.save(dfOrtGrAininessResult);
        if (save){
            dfOrtGrAininessDetailService.saveBatch(dfOrtGrAininessResult.getDfOrtGrAininessDetailList());
            return new Result(200, "bg颗粒度上传成功");
        }
        return new Result(500, "bg颗粒度上传失败");
    }
}
