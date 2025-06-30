package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfOrtClArityDetail;
import com.biel.qmsgather.domain.DfOrtClArityResult;
import com.biel.qmsgather.domain.DfOrtGlossDetail;
import com.biel.qmsgather.domain.DfOrtGlossResult;
import com.biel.qmsgather.domain.DfOrtGrAininessDetail;
import com.biel.qmsgather.domain.DfOrtGrAininessResult;
import com.biel.qmsgather.domain.vo.DfUpBgClarityParticleGlossDetailVo;
import com.biel.qmsgather.domain.vo.DfUpBgClarityParticleGlossQueryVo;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

        dfOrtClArityResult.setOkNum(dfUpBgClarityParticleGlossVo.getOkNum());
        dfOrtClArityResult.setTestNum(dfUpBgClarityParticleGlossVo.getTestNum());
        dfOrtClArityResult.setOkRate(dfUpBgClarityParticleGlossVo.getOkRate());
        dfOrtClArityResult.setTestTime(dfUpBgClarityParticleGlossVo.getTestTime());
        if (dfUpBgClarityParticleGlossVo.getOkRate() == 1) {
            dfOrtClArityResult.setResult("OK");
        }else {
            dfOrtClArityResult.setResult("NG");
        }

        // gloss
        dfOrtGlossResult.setBatch(batchFromDate);
        dfOrtGlossResult.setProcess(dfUpBgClarityParticleGlossVo.getProcess());
        dfOrtGlossResult.setColor(dfUpBgClarityParticleGlossVo.getColor());
        dfOrtGlossResult.setProject(dfUpBgClarityParticleGlossVo.getProject());
        dfOrtGlossResult.setDayOrNight(dfUpBgClarityParticleGlossVo.getDayOrNight());
        dfOrtGlossResult.setResult(dfUpBgClarityParticleGlossVo.getResult());

        dfOrtGlossResult.setTestNum(dfUpBgClarityParticleGlossVo.getTestNum());
        dfOrtGlossResult.setOkRate(dfUpBgClarityParticleGlossVo.getOkRate());
        dfOrtGlossResult.setTestTime(dfUpBgClarityParticleGlossVo.getTestTime());
        if (dfUpBgClarityParticleGlossVo.getOkRate() == 1) {
            dfOrtGlossResult.setResult("OK");
        }else {
            dfOrtGlossResult.setResult("NG");
        }

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
        dfOrtGrAininessResult.setTestTime(dfUpBgClarityParticleGlossVo.getTestTime());
        dfOrtGrAininessResult.setTestTime(dfUpBgClarityParticleGlossVo.getTestTime());
        if (dfUpBgClarityParticleGlossVo.getOkRate() == 1) {
            dfOrtGrAininessResult.setResult("OK");
        }else {
            dfOrtGrAininessResult.setResult("NG");
        }

        // 遍历 detailVo 列表，生成 detail 数据
        for (DfUpBgClarityParticleGlossDetailVo detailVo : dfUpBgClarityParticleGlossVo.getDfUpBgClarityParticleGlossDetailVo()) {
            // 白班/晚班 → A/B
            String dayOrNightCode = "白班".equals(detailVo.getDayOrNight()) ? "A" : "B";

            // clarity detail
            DfOrtClArityDetail clarityDetail = new DfOrtClArityDetail();
            clarityDetail.setBatch(batchFromDate);
            clarityDetail.setProcess(detailVo.getProcess());
            clarityDetail.setColor(detailVo.getColor());
            clarityDetail.setProject(detailVo.getProject());
            clarityDetail.setTestTime(detailVo.getTestTime());
            clarityDetail.setDayOrNight(dayOrNightCode);
            clarityDetail.setFactoryTestTime(batchFromDate + dayOrNightCode);
            clarityDetail.setValue(detailVo.getDefinition());
            dfOrtClArityResult.getDfOrtClArityDetailList().add(clarityDetail);

            // gloss detail
            DfOrtGlossDetail glossDetail = new DfOrtGlossDetail();
            glossDetail.setBatch(batchFromDate);
            glossDetail.setProcess(detailVo.getProcess());
            glossDetail.setColor(detailVo.getColor());
            glossDetail.setProject(detailVo.getProject());
            glossDetail.setTestTime(detailVo.getTestTime());
            glossDetail.setDayOrNight(dayOrNightCode);
            glossDetail.setFactoryTestTime(batchFromDate + dayOrNightCode);
            glossDetail.setValue(detailVo.getGloss());
            dfOrtGlossResult.getDfOrtGlossDetailList().add(glossDetail);

            // graininess detail
            DfOrtGrAininessDetail graininessDetail = new DfOrtGrAininessDetail();
            graininessDetail.setBatch(batchFromDate);
            graininessDetail.setProcess(detailVo.getProcess());
            graininessDetail.setColor(detailVo.getColor());
            graininessDetail.setProject(detailVo.getProject());
            graininessDetail.setTestTime(detailVo.getTestTime());
            graininessDetail.setDayOrNight(dayOrNightCode);
            graininessDetail.setFactoryTestTime(batchFromDate + dayOrNightCode);
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


    @GetMapping("/getDfUpBgClarityParticleGlossPage")
    @ApiOperation(value = "分页查询清晰度+颗粒度+光泽度（按批次号筛选）")
    public Result getDfUpBgClarityParticleGlossPage(
            @RequestParam int pageNo,
            @RequestParam int pageSize,
            @RequestParam(required = false) String batch
    ) {
        QueryWrapper<DfOrtClArityResult> clarityWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(batch)) {
            clarityWrapper.like("batch", batch);
        }
        clarityWrapper.orderByDesc("test_time");

        // 分页查清晰度 result
        IPage<DfOrtClArityResult> clarityPage = dfOrtClArityResultService.page(new Page<>(pageNo, pageSize), clarityWrapper);

        List<DfUpBgClarityParticleGlossQueryVo> resultList = new ArrayList<>();

        for (DfOrtClArityResult clarityResult : clarityPage.getRecords()) {
            DfUpBgClarityParticleGlossQueryVo vo = new DfUpBgClarityParticleGlossQueryVo();
            String currentBatch = clarityResult.getBatch();

            // 清晰度
            vo.setClarityResult(clarityResult);
            List<DfOrtClArityDetail> clarityDetailList = dfOrtClArityDetailService.list(
                    new QueryWrapper<DfOrtClArityDetail>().eq("batch", currentBatch)
            );
            vo.setClarityDetailList(clarityDetailList);

            // 光泽度
            DfOrtGlossResult glossResult = dfOrtGlossResultService.getOne(
                    new QueryWrapper<DfOrtGlossResult>().eq("batch", currentBatch)
            );
            vo.setGlossResult(glossResult);
            List<DfOrtGlossDetail> glossDetailList = dfOrtGlossDetailService.list(
                    new QueryWrapper<DfOrtGlossDetail>().eq("batch", currentBatch)
            );
            vo.setGlossDetailList(glossDetailList);

            // 颗粒度
            DfOrtGrAininessResult graininessResult = dfOrtGrAininessResultService.getOne(
                    new QueryWrapper<DfOrtGrAininessResult>().eq("batch", currentBatch)
            );
            vo.setGraininessResult(graininessResult);
            List<DfOrtGrAininessDetail> graininessDetailList = dfOrtGrAininessDetailService.list(
                    new QueryWrapper<DfOrtGrAininessDetail>().eq("batch", currentBatch)
            );
            vo.setGraininessDetailList(graininessDetailList);

            resultList.add(vo);
        }

        // 封装分页返回
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", clarityPage.getTotal());
        resultMap.put("records", resultList);

        return new Result(200, "查询成功", resultMap);
    }



    @PostMapping("/deleteDfOrtClarity")
    @ApiOperation(value = "删除清晰度记录（真删除，支持单个/批量）")
    public Result deleteDfOrtClarity(@RequestBody List<String> batchList) {
        if (batchList == null || batchList.isEmpty()) {
            return new Result(400, "批次号不能为空");
        }

        // 删 result
        dfOrtClArityResultService.remove(new QueryWrapper<DfOrtClArityResult>().in("batch", batchList));
        // 删 detail
        dfOrtClArityDetailService.remove(new QueryWrapper<DfOrtClArityDetail>().in("batch", batchList));

        return new Result(200, "删除成功");
    }

    @PostMapping("/deleteDfUpBgClarityParticleGloss")
    @ApiOperation(value = "删除清晰度+颗粒度+光泽度记录（真删除，支持单个/批量）")
    public Result deleteDfUpBgClarityParticleGloss(@RequestBody List<String> batchList) {
        if (batchList == null || batchList.isEmpty()) {
            return new Result(400, "批次号不能为空");
        }

        // 删除清晰度
        dfOrtClArityResultService.remove(new QueryWrapper<DfOrtClArityResult>().in("batch", batchList));
        dfOrtClArityDetailService.remove(new QueryWrapper<DfOrtClArityDetail>().in("batch", batchList));

        // 删除光泽度
        dfOrtGlossResultService.remove(new QueryWrapper<DfOrtGlossResult>().in("batch", batchList));
        dfOrtGlossDetailService.remove(new QueryWrapper<DfOrtGlossDetail>().in("batch", batchList));

        // 删除颗粒度
        dfOrtGrAininessResultService.remove(new QueryWrapper<DfOrtGrAininessResult>().in("batch", batchList));
        dfOrtGrAininessDetailService.remove(new QueryWrapper<DfOrtGrAininessDetail>().in("batch", batchList));

        return new Result(200, "清晰度+颗粒度+光泽度 删除成功");
    }

    @PostMapping("/updateDfUpBgClarityParticleGloss")
    @ApiOperation(value = "修改清晰度+颗粒度+光泽度数据")
    public Result updateDfUpBgClarityParticleGloss(@RequestBody DfUpBgClarityParticleGlossVo vo) {
        // 1. 根据传过来的主表ID，先查主表
        if (vo.getId() == null) {
            return new Result(400, "ID不能为空");
        }

        DfOrtClArityResult existingClarity = dfOrtClArityResultService.getById(vo.getId());
        if (existingClarity == null) {
            return new Result(404, "数据不存在");
        }

        // 2. 获取batch
        String batch = existingClarity.getBatch();

        // 3. 更新主表字段
        BeanUtils.copyProperties(vo, existingClarity, "id", "batch"); // 不能修改id和batch
        existingClarity.setTestTime(vo.getTestTime());
        existingClarity.setResult(vo.getOkRate() == 1 ? "OK" : "NG");
        existingClarity.setOkNum(vo.getOkNum());
        existingClarity.setTestNum(vo.getTestNum());
        existingClarity.setOkRate(vo.getOkRate());
        existingClarity.setProcess(vo.getProcess());
        existingClarity.setColor(vo.getColor());
        existingClarity.setProject(vo.getProject());
        existingClarity.setDayOrNight(vo.getDayOrNight());

        boolean clarityUpdate = dfOrtClArityResultService.updateById(existingClarity);

        // 4. 删除对应 batch 的旧明细
        dfOrtClArityDetailService.remove(new QueryWrapper<DfOrtClArityDetail>().eq("batch", batch));
        dfOrtGlossDetailService.remove(new QueryWrapper<DfOrtGlossDetail>().eq("batch", batch));
        dfOrtGrAininessDetailService.remove(new QueryWrapper<DfOrtGrAininessDetail>().eq("batch", batch));

        // 5. 新增新的明细
        List<DfOrtClArityDetail> clarityDetails = new ArrayList<>();
        List<DfOrtGlossDetail> glossDetails = new ArrayList<>();
        List<DfOrtGrAininessDetail> graininessDetails = new ArrayList<>();

        for (DfUpBgClarityParticleGlossDetailVo detailVo : vo.getDfUpBgClarityParticleGlossDetailVo()) {
            String dayOrNightCode = "白班".equals(detailVo.getDayOrNight()) ? "A" : "B";

            DfOrtClArityDetail clarityDetail = new DfOrtClArityDetail();
            clarityDetail.setBatch(batch);
            clarityDetail.setProcess(detailVo.getProcess());
            clarityDetail.setColor(detailVo.getColor());
            clarityDetail.setProject(detailVo.getProject());
            clarityDetail.setTestTime(detailVo.getTestTime());
            clarityDetail.setDayOrNight(dayOrNightCode);
            clarityDetail.setFactoryTestTime(batch + dayOrNightCode);
            clarityDetail.setValue(detailVo.getDefinition());
            clarityDetails.add(clarityDetail);

            DfOrtGlossDetail glossDetail = new DfOrtGlossDetail();
            glossDetail.setBatch(batch);
            glossDetail.setProcess(detailVo.getProcess());
            glossDetail.setColor(detailVo.getColor());
            glossDetail.setProject(detailVo.getProject());
            glossDetail.setTestTime(detailVo.getTestTime());
            glossDetail.setDayOrNight(dayOrNightCode);
            glossDetail.setFactoryTestTime(batch + dayOrNightCode);
            glossDetail.setValue(detailVo.getGloss());
            glossDetails.add(glossDetail);

            DfOrtGrAininessDetail graininessDetail = new DfOrtGrAininessDetail();
            graininessDetail.setBatch(batch);
            graininessDetail.setProcess(detailVo.getProcess());
            graininessDetail.setColor(detailVo.getColor());
            graininessDetail.setProject(detailVo.getProject());
            graininessDetail.setTestTime(detailVo.getTestTime());
            graininessDetail.setDayOrNight(dayOrNightCode);
            graininessDetail.setFactoryTestTime(batch + dayOrNightCode);
            graininessDetail.setValue(detailVo.getGranularity());
            graininessDetails.add(graininessDetail);
        }

        boolean clarityDetailSave = dfOrtClArityDetailService.saveBatch(clarityDetails);
        boolean glossDetailSave = dfOrtGlossDetailService.saveBatch(glossDetails);
        boolean graininessDetailSave = dfOrtGrAininessDetailService.saveBatch(graininessDetails);

        if (clarityUpdate && clarityDetailSave && glossDetailSave && graininessDetailSave) {
            return new Result(200, "修改成功");
        } else {
            return new Result(500, "修改失败");
        }
    }


    @GetMapping("/getDfUpBgClarityParticleGlossById")
    @ApiOperation(value = "根据ID查询清晰度+颗粒度+光泽度详情")
    public Result getById(@RequestParam Long id) {
        DfOrtClArityResult result = dfOrtClArityResultService.getById(id);
        if (result == null) {
            return new Result(404, "数据不存在");
        }

        // 查询对应的明细
        List<DfOrtClArityDetail> clarityDetails = dfOrtClArityDetailService.list(
                new QueryWrapper<DfOrtClArityDetail>()
                        .eq("batch", result.getBatch())
                        .eq("is_deleted", 0)
        );

        // 同理查 gloss、graininess 明细
        List<DfOrtGlossDetail> glossDetails = dfOrtGlossDetailService.list(
                new QueryWrapper<DfOrtGlossDetail>()
                        .eq("batch", result.getBatch())
                        .eq("is_deleted", 0)
        );

        List<DfOrtGrAininessDetail> graininessDetails = dfOrtGrAininessDetailService.list(
                new QueryWrapper<DfOrtGrAininessDetail>()
                        .eq("batch", result.getBatch())
                        .eq("is_deleted", 0)
        );

        // 封装VO返回前端
        DfUpBgClarityParticleGlossVo vo = new DfUpBgClarityParticleGlossVo();
        BeanUtils.copyProperties(result, vo);
        vo.setDfUpBgClarityParticleGlossDetailVo(new ArrayList<>());

        // 明细组装成VO
        for (int i = 0; i < clarityDetails.size(); i++) {
            DfUpBgClarityParticleGlossDetailVo detailVo = new DfUpBgClarityParticleGlossDetailVo();
            detailVo.setDefinition(clarityDetails.get(i).getValue());
            detailVo.setGloss(glossDetails.get(i).getValue());
            detailVo.setGranularity(graininessDetails.get(i).getValue());
            detailVo.setTestTime(clarityDetails.get(i).getTestTime());
            detailVo.setDayOrNight("A".equals(clarityDetails.get(i).getDayOrNight()) ? "白班" : "晚班");
            detailVo.setProcess(clarityDetails.get(i).getProcess());
            detailVo.setColor(clarityDetails.get(i).getColor());
            detailVo.setProject(clarityDetails.get(i).getProject());

            vo.getDfUpBgClarityParticleGlossDetailVo().add(detailVo);
        }

        return new Result(200, "查询成功", vo);
    }




}
