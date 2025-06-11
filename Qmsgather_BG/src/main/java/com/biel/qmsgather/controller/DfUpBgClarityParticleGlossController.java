package com.biel.qmsgather.controller;

import com.biel.qmsgather.domain.DfOrtClArityDetail;
import com.biel.qmsgather.domain.DfOrtClArityResult;
import com.biel.qmsgather.domain.DfOrtGlossDetail;
import com.biel.qmsgather.domain.DfOrtGlossResult;
import com.biel.qmsgather.domain.DfOrtGrAininessDetail;
import com.biel.qmsgather.domain.DfOrtGrAininessResult;
import com.biel.qmsgather.domain.vo.DfUpBgClarityParticleGlossDetailVo;
import com.biel.qmsgather.domain.vo.DfUpBgClarityParticleGlossVo;
import com.biel.qmsgather.service.DfOrtClArityDetailService;
import com.biel.qmsgather.service.DfOrtClArityResultService;
import com.biel.qmsgather.service.DfOrtGlossDetailService;
import com.biel.qmsgather.service.DfOrtGlossResultService;
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

import java.util.ArrayList;


@RestController
@RequestMapping("/bg/dfUpBgClarityParticleGloss")
@CrossOrigin
@Api(tags = "清晰度+颗粒度+光泽度")
public class DfUpBgClarityParticleGlossController {

    @Autowired
    private DfOrtClArityDetailService dfOrtClArityDetailService;

    @Autowired
    private DfOrtClArityResultService dfOrtClArityResultService;

    @Autowired
    private DfOrtGlossDetailService dfOrtGlossDetailService;

    @Autowired
    private DfOrtGlossResultService dfOrtGlossResultService;

    @Autowired
    private DfOrtGrAininessDetailService dfOrtGrAininessDetailService;

    @Autowired
    private DfOrtGrAininessResultService dfOrtGrAininessResultService;


    @PostMapping("/uploadDfUpBgClarityParticleGloss")
    @ApiOperation(value = "清晰度+颗粒度+光泽度")
    public Result uploadDfUpBgClarityParticleGloss(@RequestBody DfUpBgClarityParticleGlossVo dfUpBgClarityParticleGlossVo){
        // 批次号
        String batchFromDate = DateUtil.getBatchFromDate(dfUpBgClarityParticleGlossVo.getTestTime());

        // 初始化结果对象，顺便初始化 detail list
        DfOrtClArityResult dfOrtClArityResult = new DfOrtClArityResult();
        dfOrtClArityResult.setDfOrtClArityDetailList(new ArrayList<>());

        DfOrtGlossResult dfOrtGlossResult = new DfOrtGlossResult();
        dfOrtGlossResult.setDfOrtGlossDetailList(new ArrayList<>());

        DfOrtGrAininessResult dfOrtGrAininessResult = new DfOrtGrAininessResult();
        dfOrtGrAininessResult.setDfOrtGrAininessDetailList(new ArrayList<>());

        // 公共字段赋值
        dfOrtClArityResult.setBatch(batchFromDate);
        dfOrtClArityResult.setProcess(dfUpBgClarityParticleGlossVo.getProcess());
        dfOrtClArityResult.setColor(dfUpBgClarityParticleGlossVo.getColor());
        dfOrtClArityResult.setProject(dfUpBgClarityParticleGlossVo.getProject());
        dfOrtClArityResult.setDayOrNight(dfUpBgClarityParticleGlossVo.getDayOrNight());
        dfOrtClArityResult.setResult(dfUpBgClarityParticleGlossVo.getResult());
        dfOrtClArityResult.setOkNum(dfUpBgClarityParticleGlossVo.getOkNum());
        dfOrtClArityResult.setTestNum(dfUpBgClarityParticleGlossVo.getTestNum());
        dfOrtClArityResult.setOkRate(dfUpBgClarityParticleGlossVo.getOkRate());

        // gloss
        dfOrtGlossResult.setBatch(batchFromDate);
        dfOrtGlossResult.setProcess(dfUpBgClarityParticleGlossVo.getProcess());
        dfOrtGlossResult.setColor(dfUpBgClarityParticleGlossVo.getColor());
        dfOrtGlossResult.setProject(dfUpBgClarityParticleGlossVo.getProject());
        dfOrtGlossResult.setDayOrNight(dfUpBgClarityParticleGlossVo.getDayOrNight());
        dfOrtGlossResult.setResult(dfUpBgClarityParticleGlossVo.getResult());
        dfOrtGlossResult.setOkNum(dfUpBgClarityParticleGlossVo.getOkNum());
        dfOrtGlossResult.setTestNum(dfUpBgClarityParticleGlossVo.getTestNum());
        dfOrtGlossResult.setOkRate(dfUpBgClarityParticleGlossVo.getOkRate());

        // graininess
        dfOrtGrAininessResult.setBatch(batchFromDate);
        dfOrtGrAininessResult.setProcess(dfUpBgClarityParticleGlossVo.getProcess());
        dfOrtGrAininessResult.setColor(dfUpBgClarityParticleGlossVo.getColor());
        dfOrtGrAininessResult.setProject(dfUpBgClarityParticleGlossVo.getProject());
        dfOrtGrAininessResult.setDayOrNight(dfUpBgClarityParticleGlossVo.getDayOrNight());
        dfOrtGrAininessResult.setResult(dfUpBgClarityParticleGlossVo.getResult());
        dfOrtGrAininessResult.setOkNum(dfUpBgClarityParticleGlossVo.getOkNum());
        dfOrtGrAininessResult.setTestNum(dfUpBgClarityParticleGlossVo.getTestNum());
        dfOrtGrAininessResult.setOkRate(dfUpBgClarityParticleGlossVo.getOkRate());

        // 遍历 detailVo 列表，生成 detail 数据
        for (DfUpBgClarityParticleGlossDetailVo detailVo : dfUpBgClarityParticleGlossVo.getDfUpBgClarityParticleGlossDetailVo()) {
            // 白班/晚班 → A/B
            String dayOrNightCode = "白班".equals(detailVo.getDayOrNight()) ? "A" : "B";

            // clarity detail
            DfOrtClArityDetail clarityDetail = new DfOrtClArityDetail();
            clarityDetail.setBatch(detailVo.getBatch());
            clarityDetail.setProcess(detailVo.getProcess());
            clarityDetail.setColor(detailVo.getColor());
            clarityDetail.setProject(detailVo.getProject());
            clarityDetail.setTestTime(detailVo.getTestTime());
            clarityDetail.setDayOrNight(dayOrNightCode);
            clarityDetail.setFactoryTestTime(detailVo.getBatch() + dayOrNightCode);
            clarityDetail.setValue(detailVo.getDefinition());
            dfOrtClArityResult.getDfOrtClArityDetailList().add(clarityDetail);

            // gloss detail
            DfOrtGlossDetail glossDetail = new DfOrtGlossDetail();
            glossDetail.setBatch(detailVo.getBatch());
            glossDetail.setProcess(detailVo.getProcess());
            glossDetail.setColor(detailVo.getColor());
            glossDetail.setProject(detailVo.getProject());
            glossDetail.setTestTime(detailVo.getTestTime());
            glossDetail.setDayOrNight(dayOrNightCode);
            glossDetail.setFactoryTestTime(detailVo.getBatch() + dayOrNightCode);
            glossDetail.setValue(detailVo.getGloss());
            dfOrtGlossResult.getDfOrtGlossDetailList().add(glossDetail);

            // graininess detail
            DfOrtGrAininessDetail graininessDetail = new DfOrtGrAininessDetail();
            graininessDetail.setBatch(detailVo.getBatch());
            graininessDetail.setProcess(detailVo.getProcess());
            graininessDetail.setColor(detailVo.getColor());
            graininessDetail.setProject(detailVo.getProject());
            graininessDetail.setTestTime(detailVo.getTestTime());
            graininessDetail.setDayOrNight(dayOrNightCode);
            graininessDetail.setFactoryTestTime(detailVo.getBatch() + dayOrNightCode);
            graininessDetail.setValue(detailVo.getGranularity());
            dfOrtGrAininessResult.getDfOrtGrAininessDetailList().add(graininessDetail);
        }

        // 保存 result 表数据
        boolean saveClarity = dfOrtClArityResultService.save(dfOrtClArityResult);
        boolean saveGloss = dfOrtGlossResultService.save(dfOrtGlossResult);
        boolean saveGraininess = dfOrtGrAininessResultService.save(dfOrtGrAininessResult);

        // 批量保存 detail 表数据
        dfOrtClArityDetailService.saveBatch(dfOrtClArityResult.getDfOrtClArityDetailList());
        dfOrtGlossDetailService.saveBatch(dfOrtGlossResult.getDfOrtGlossDetailList());
        dfOrtGrAininessDetailService.saveBatch(dfOrtGrAininessResult.getDfOrtGrAininessDetailList());

        // 返回结果
        if (saveClarity && saveGloss && saveGraininess) {
            return new Result(200, "bg清晰度+颗粒度+光泽度上传成功");
        }
        return new Result(500, "bg清晰度+颗粒度+光泽度上传失败");
    }

}
