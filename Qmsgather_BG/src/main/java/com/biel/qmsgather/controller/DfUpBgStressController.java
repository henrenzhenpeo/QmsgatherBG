package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfOrtStressDetail;
import com.biel.qmsgather.domain.DfOrtStressResult;
import com.biel.qmsgather.domain.DfUpBgStress;
import com.biel.qmsgather.service.DfOrtStressDetailService;
import com.biel.qmsgather.service.DfOrtStressResultService;
import com.biel.qmsgather.service.DfUpBgStressService;
import com.biel.qmsgather.util.DateUtil;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bg/dfUpBgStress")
@CrossOrigin
@Api(tags = "bg应力接口")
public class DfUpBgStressController {

    @Autowired
    private DfUpBgStressService dfUpBgStressService;

    @Autowired
    private DfOrtStressDetailService dfOrtStressDetailService;

    @Autowired
    private DfOrtStressResultService dfOrtStressResultService;

    @PostMapping("/upload")
    @ApiOperation(value = "bg应力接口上传")
    public Result uploadDfUpBgStress(@RequestBody List<DfUpBgStress> dfUpBgStressList){

        String newBatchId = dfUpBgStressService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgStress dfUpBgStress : dfUpBgStressList) {
            dfUpBgStress.setBatchId(dfUpBgStress.getProductModel()+"-"+newBatchId);
        }

        boolean b = dfUpBgStressService.saveBatch(dfUpBgStressList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"bg应力接口上传成功");
        }

        return new Result(500,"bg应力接口上传失败");
    }

    @GetMapping("/findDfUpBgStress")
    @ApiOperation(value = "bg应力查询接口")
    public R findDfUpBgStress(
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "process", required = false) String process,
            @RequestParam(value = "product_model", required = false) String productModel,
            @RequestParam(value = "product_color", required = false) String productColor,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgStress> dfUpBgStressQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfUpBgStressQueryWrapper.eq("process", process);
        }
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgStressQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(productModel)) {
            dfUpBgStressQueryWrapper.eq("product_model", productModel);
        }
        if (StringUtils.isNotEmpty(productColor)) {
            dfUpBgStressQueryWrapper.eq("product_color", productColor);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgStressQueryWrapper.between("date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgStress> pageResult = dfUpBgStressService.page(
                new Page<>(page, limit),
                dfUpBgStressQueryWrapper
        );

        return R.ok(pageResult);
    }

    @PostMapping("/uploadDfOrtStressDetail")
    @ApiOperation(value = "bg应力接口上传ort")
    public Result uploadDfOrtStressDetail(@RequestBody DfOrtStressResult dfOrtStressResult){

        String batchFromDate = DateUtil.getBatchFromDate(dfOrtStressResult.getTestTime());
        dfOrtStressResult.setBatch(batchFromDate);
        for (DfOrtStressDetail dfOrtStressDetail : dfOrtStressResult.getDfOrtStressDetailList()) {
            dfOrtStressDetail.setBatch(batchFromDate);
            if (dfOrtStressDetail.getDayOrNight().equals("白班")) {
                dfOrtStressDetail.setDayOrNight("A");
            }else if (dfOrtStressDetail.getDayOrNight().equals("晚班")) {
                dfOrtStressDetail.setDayOrNight("B");
            }
            dfOrtStressDetail.setFactoryTestTime(batchFromDate+""+dfOrtStressDetail.getDayOrNight());
        }

        boolean b = dfOrtStressResultService.save(dfOrtStressResult);
        if (b) {
            dfOrtStressDetailService.saveBatch(dfOrtStressResult.getDfOrtStressDetailList());
            return new Result(200,"bg应力接口ort上传成功");
        }

        return new Result(500,"bg应力接口ort上传失败");
    }

    @GetMapping("/getDfOrtStressResultPage")
    @ApiOperation(value = "分页查询bg应力ORT记录（可按batch筛选）")
    public Result getDfOrtStressResultPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String batch) {
        QueryWrapper<DfOrtStressResult> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);
        }
        IPage<DfOrtStressResult> resultPage = dfOrtStressResultService.page(
                new Page<>(pageNum, pageSize),
                queryWrapper
        );

        return new Result(200, "查询成功", resultPage);
    }


    @GetMapping("/getDfOrtStressResultById/{id}")
    @ApiOperation(value = "根据ID查询bg应力ORT记录（含明细）")
    public Result getDfOrtStressResultById(@PathVariable Long id) {
        DfOrtStressResult result = dfOrtStressResultService.getById(id);
        if (result == null) {
            return new Result(404, "记录不存在");
        }

        List<DfOrtStressDetail> details = dfOrtStressDetailService.list(
                new QueryWrapper<DfOrtStressDetail>().eq("batch", result.getBatch())
        );
        result.setDfOrtStressDetailList(details);

        return new Result(200, "查询成功", result);
    }

    @PutMapping("/updateDfOrtStressResult")
    @ApiOperation(value = "根据ID修改bg应力ORT记录（含明细）")
    public Result updateDfOrtStressResult(@RequestBody DfOrtStressResult dfOrtStressResult) {
        if (dfOrtStressResult.getId() == null) {
            return new Result(400, "ID不能为空，无法定位修改记录");
        }

        // 重新生成批次号
        String batchFromDate = DateUtil.getBatchFromDate(dfOrtStressResult.getTestTime());
        dfOrtStressResult.setBatch(batchFromDate);

        // 修改主表
        boolean updateResult = dfOrtStressResultService.updateById(dfOrtStressResult);
        if (!updateResult) {
            return new Result(500, "修改主表失败");
        }

        // 删除旧明细
        dfOrtStressDetailService.remove(
                new QueryWrapper<DfOrtStressDetail>().eq("batch", batchFromDate)
        );

        // 新增现有明细
        for (DfOrtStressDetail detail : dfOrtStressResult.getDfOrtStressDetailList()) {
            detail.setBatch(batchFromDate);
            if ("白班".equals(detail.getDayOrNight())) {
                detail.setDayOrNight("A");
            } else if ("晚班".equals(detail.getDayOrNight())) {
                detail.setDayOrNight("B");
            }
            detail.setFactoryTestTime(batchFromDate + detail.getDayOrNight());
        }

        dfOrtStressDetailService.saveBatch(dfOrtStressResult.getDfOrtStressDetailList());

        return new Result(200, "修改成功");
    }

    @DeleteMapping("/deleteDfOrtStressResultBatch")
    @ApiOperation(value = "批量删除bg应力ORT记录（含明细）")
    public Result deleteDfOrtStressResultBatch(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new Result(400, "ID列表不能为空");
        }

        // 查询所有主表记录，拿批次号
        List<DfOrtStressResult> results = new ArrayList<>(dfOrtStressResultService.listByIds(ids));
        if (results.isEmpty()) {
            return new Result(404, "记录不存在");
        }

        List<String> batches = results.stream()
                .map(DfOrtStressResult::getBatch)
                .collect(Collectors.toList());

        // 删除所有明细
        dfOrtStressDetailService.remove(
                new QueryWrapper<DfOrtStressDetail>().in("batch", batches)
        );

        // 删除所有主表
        boolean removeResult = dfOrtStressResultService.removeByIds(ids);

        return removeResult ? new Result(200, "批量删除成功") : new Result(500, "批量删除失败");
    }


}
