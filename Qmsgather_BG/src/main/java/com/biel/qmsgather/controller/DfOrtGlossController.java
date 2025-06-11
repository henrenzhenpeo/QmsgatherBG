package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfOrtGlossDetail;
import com.biel.qmsgather.domain.DfOrtGlossResult;
import com.biel.qmsgather.domain.vo.DfUpBgClarityParticleGlossVo;
import com.biel.qmsgather.service.DfOrtGlossDetailService;
import com.biel.qmsgather.service.DfOrtGlossResultService;
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
@RequestMapping("/bg/dfOrtGloss")
@CrossOrigin
@Api(tags = "BG ort光泽度接口")
public class DfOrtGlossController {

    @Autowired
    private DfOrtGlossResultService dfOrtGlossResultService;

    @Autowired
    private DfOrtGlossDetailService dfOrtGlossDetailService;

    @PostMapping("/uploadDfOrtGloss")
    @ApiOperation(value = "bg光泽度上传接口")
    public Result uploadDfOrtGloss(@RequestBody DfOrtGlossResult dfOrtGlossResult){
        String batchFromDate = DateUtil.getBatchFromDate(dfOrtGlossResult.getTestTime());
        dfOrtGlossResult.setBatch(batchFromDate);
        for (DfOrtGlossDetail dfOrtGlossDetail : dfOrtGlossResult.getDfOrtGlossDetailList()) {
            dfOrtGlossDetail.setBatch(batchFromDate);
        }
        boolean save = dfOrtGlossResultService.save(dfOrtGlossResult);
        if (save){
            dfOrtGlossDetailService.saveBatch(dfOrtGlossResult.getDfOrtGlossDetailList());
            return new Result(200, "bg光泽度上传成功");
        }
        return new Result(500, "bg光泽度上传失败");
    }

}
