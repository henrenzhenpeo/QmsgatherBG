package com.biel.qmsgather.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfOrtFqcOpticalDensity;
import com.biel.qmsgather.domain.DfOrtGrAininessDetail;
import com.biel.qmsgather.domain.DfOrtOpticalDensity;
import com.biel.qmsgather.domain.DfOrtOpticalDensityResult;
import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.biel.qmsgather.service.DfOrtFqcOpticalDensityService;
import com.biel.qmsgather.service.DfOrtOpticalDensityResultService;
import com.biel.qmsgather.service.DfOrtOpticalDensityService;
import com.biel.qmsgather.service.DfUpBgInkDensityService;
import com.biel.qmsgather.util.DateUtil;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bg/dfUpBgInkDensity")
@CrossOrigin
@Api(tags = "OD密度接口")
public class DfUpBgInkDensityController {


    @Autowired
    private DfUpBgInkDensityService dfUpBgInkDensityService;

    @Autowired
    private DfOrtFqcOpticalDensityService dfOrtFqcOpticalDensityService;

    @Autowired
    private DfOrtOpticalDensityService dfOrtOpticalDensityService;

    @Autowired
    private DfOrtOpticalDensityResultService dfOrtOpticalDensityResultService;

    @PostMapping("/upload")
    @ApiOperation(value = "OD密度接口接口上传")
    public Result uploadDfUpBgInkDensity(@RequestBody List<DfUpBgInkDensity> dfUpBgInkDensityList){

        String newBatchId = dfUpBgInkDensityService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgInkDensity dfUpBgInkDensity : dfUpBgInkDensityList) {
            dfUpBgInkDensity.setBatchId(newBatchId);
        }

        boolean b = dfUpBgInkDensityService.saveBatch(dfUpBgInkDensityList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"OD密度接口上传成功");
        }

        return new Result(500,"OD密度接口上传失败");
    }

    @PostMapping("/uploadDfOrtFqcOpticalDensity")
    @ApiOperation(value = "OD密度接口接口上传IPQC")
    public Result uploadDfOrtFqcOpticalDensity(@RequestBody List<DfOrtFqcOpticalDensity> dfOrtFqcOpticalDensities){

        for (DfOrtFqcOpticalDensity dfOrtFqcOpticalDensity : dfOrtFqcOpticalDensities) {
            Date checkTime = dfOrtFqcOpticalDensity.getCheckTime();
            String batchFromDate = DateUtil.getBatchFromDate(checkTime);
            dfOrtFqcOpticalDensity.setBatch(batchFromDate);
        }

        boolean b = dfOrtFqcOpticalDensityService.saveBatch(dfOrtFqcOpticalDensities);
        if (b) {
            return new Result(200,"OD密度接口上传成功");
        }

        return new Result(500,"OD密度接口上传失败");
    }

    @PostMapping("/uploadDfOrtOpticalDensityResult")
    @ApiOperation(value = "OD密度接口接口上传OQC")
    public Result uploadDfOrtOpticalDensityResult(@RequestBody DfOrtOpticalDensityResult dfOrtOpticalDensityResult){

        String batchFromDate = DateUtil.getBatchFromDate(dfOrtOpticalDensityResult.getCheckTime());
        dfOrtOpticalDensityResult.setBatch(batchFromDate);
        for (DfOrtOpticalDensity dfOrtOpticalDensity : dfOrtOpticalDensityResult.getDfOrtOpticalDensityList()) {
            dfOrtOpticalDensity.setBatch(batchFromDate);
        }
        boolean b = dfOrtOpticalDensityResultService.save(dfOrtOpticalDensityResult);
        if (b) {
            dfOrtOpticalDensityService.saveBatch(dfOrtOpticalDensityResult.getDfOrtOpticalDensityList());
            return new Result(200,"OD密度上传OQC上传成功");
        }

        return new Result(500,"OD密度上传OQC上传失败");
    }



    @GetMapping("/findDfUpBgInkDensity")
    @ApiOperation(value = "OD密度查询接口")
    public R findDfUpBgInkDensity(
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgInkDensity> dfUpBgInkDensityQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgInkDensityQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgInkDensityQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgInkDensityQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgInkDensityQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgInkDensityQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgInkDensity> pageResult = dfUpBgInkDensityService.page(
                new Page<>(page, limit),
                dfUpBgInkDensityQueryWrapper
        );

        return R.ok(pageResult);
    }


    @GetMapping("/pageDfOrtFqcOpticalDensity")
    @ApiOperation(value = "分页查询OD密度IPQC记录，支持按batch模糊搜索")
    public Result pageDfOrtFqcOpticalDensity(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String batch) {

        QueryWrapper<DfOrtFqcOpticalDensity> queryWrapper = new QueryWrapper<>();
        if (batch != null && !batch.isEmpty()) {
            queryWrapper.like("batch", batch);
        }
        IPage<DfOrtFqcOpticalDensity> resultPage = dfOrtFqcOpticalDensityService.page(
                new Page<>(pageNum, pageSize),
                queryWrapper
        );
        return new Result(200, "查询成功", resultPage);
    }

    @GetMapping("/pageDfOrtOpticalDensityResult")
    @ApiOperation(value = "分页查询OD密度OQC结果，附带明细，支持按batch模糊搜索")
    public Result pageDfOrtOpticalDensityResult(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String batch) {

        QueryWrapper<DfOrtOpticalDensityResult> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);
        }

        IPage<DfOrtOpticalDensityResult> resultPage = dfOrtOpticalDensityResultService.page(
                new Page<>(pageNum, pageSize),
                queryWrapper
        );

        // 查询明细并封装
        for (DfOrtOpticalDensityResult result : resultPage.getRecords()) {
            List<DfOrtOpticalDensity> detailList = dfOrtOpticalDensityService.list(
                    new QueryWrapper<DfOrtOpticalDensity>()
                            .eq("batch", result.getBatch())
            );
            result.setDfOrtOpticalDensityList(detailList);
        }

        return new Result(200, "分页查询成功", resultPage);
    }



    @GetMapping("/getDfOrtFqcOpticalDensityById/{id}")
    @ApiOperation(value = "根据ID查询OD密度IPQC记录")
    public Result getDfOrtFqcOpticalDensityById(@PathVariable Long id) {
        DfOrtFqcOpticalDensity data = dfOrtFqcOpticalDensityService.getById(id);
        if (data != null) {
            return new Result(200, "查询成功", data);
        }
        return new Result(404, "未找到记录");
    }

    @PutMapping("/updateDfOrtFqcOpticalDensity")
    @ApiOperation(value = "修改OD密度IPQC记录")
    public Result updateDfOrtFqcOpticalDensity(@RequestBody DfOrtFqcOpticalDensity dfOrtFqcOpticalDensity) {
        if (dfOrtFqcOpticalDensity.getId() == null) {
            return new Result(400, "ID不能为空");
        }
        // 更新批次号
        String batchFromDate = DateUtil.getBatchFromDate(dfOrtFqcOpticalDensity.getCheckTime());
        dfOrtFqcOpticalDensity.setBatch(batchFromDate);

        boolean result = dfOrtFqcOpticalDensityService.updateById(dfOrtFqcOpticalDensity);
        return result ? new Result(200, "修改成功") : new Result(500, "修改失败");
    }


    @DeleteMapping("/deleteDfOrtFqcOpticalDensity")
    @ApiOperation(value = "批量删除OD密度IPQC记录")
    public Result deleteDfOrtFqcOpticalDensity(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new Result(400, "ID列表不能为空");
        }
        boolean result = dfOrtFqcOpticalDensityService.removeByIds(ids);
        return result ? new Result(200, "删除成功") : new Result(500, "删除失败");
    }


    @GetMapping("/getDfOrtOpticalDensityResultById/{id}")
    @ApiOperation(value = "根据ID查询OD密度OQC记录（含明细）")
    public Result getDfOrtOpticalDensityResultById(@PathVariable Long id) {
        DfOrtOpticalDensityResult result = dfOrtOpticalDensityResultService.getById(id);
        if (result != null) {
            // 查询明细
            List<DfOrtOpticalDensity> details = dfOrtOpticalDensityService.list(
                    new QueryWrapper<DfOrtOpticalDensity>().eq("batch", result.getBatch())
            );
            result.setDfOrtOpticalDensityList(details);
            return new Result(200, "查询成功", result);
        }
        return new Result(404, "未找到记录");
    }


    @PutMapping("/updateDfOrtOpticalDensityResult")
    @ApiOperation(value = "修改OD密度OQC记录（含明细）")
    public Result updateDfOrtOpticalDensityResult(@RequestBody DfOrtOpticalDensityResult result) {
        if (result.getId() == null) {
            return new Result(400, "ID不能为空");
        }

        // 更新主表
        String batchFromDate = DateUtil.getBatchFromDate(result.getCheckTime());
        result.setBatch(batchFromDate);
        boolean updateMain = dfOrtOpticalDensityResultService.updateById(result);
        if (!updateMain) {
            return new Result(500, "主表更新失败");
        }

        // 删除旧明细（真删）
        dfOrtOpticalDensityService.remove(new QueryWrapper<DfOrtOpticalDensity>().eq("batch", result.getBatch()));

        // 新增明细
        for (DfOrtOpticalDensity detail : result.getDfOrtOpticalDensityList()) {
            detail.setBatch(batchFromDate);
        }
        dfOrtOpticalDensityService.saveBatch(result.getDfOrtOpticalDensityList());

        return new Result(200, "修改成功");
    }


    @DeleteMapping("/deleteDfOrtOpticalDensityResultBatch")
    @ApiOperation(value = "批量删除OD密度OQC记录（含明细）")
    public Result deleteDfOrtOpticalDensityResultBatch(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new Result(400, "ID列表不能为空");
        }

        // 查询所有主表记录，拿批次号
        Collection<DfOrtOpticalDensityResult> results = dfOrtOpticalDensityResultService.listByIds(ids);

        if (results.isEmpty()) {
            return new Result(404, "记录不存在");
        }
        List<String> batches = results.stream()
                .map(DfOrtOpticalDensityResult::getBatch)
                .collect(Collectors.toList());
        // 删除所有明细
        dfOrtOpticalDensityService.remove(
                new QueryWrapper<DfOrtOpticalDensity>().in("batch", batches)
        );

        // 删除所有主表
        boolean removeResult = dfOrtOpticalDensityResultService.removeByIds(ids);

        return removeResult ? new Result(200, "批量删除成功") : new Result(500, "批量删除失败");
    }




    @GetMapping("/exportDfOrtFqcOpticalDensity")
    @ApiOperation(value = "导出OD密度IPQC记录")
    public void exportDfOrtFqcOpticalDensity(
            @RequestParam(required = false) String batch,
            @RequestParam(required = false) String project,
            @RequestParam(required = false) String color,
            HttpServletResponse response
    ) throws IOException {
        // 构建查询条件
        QueryWrapper<DfOrtFqcOpticalDensity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(batch)) {
            queryWrapper.like("batch", batch);
        }
        if (StringUtils.isNotEmpty(project)) {
            queryWrapper.like("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            queryWrapper.like("color", color);
        }
        queryWrapper.orderByDesc("check_time");

        // 查询数据
        List<DfOrtFqcOpticalDensity> list = dfOrtFqcOpticalDensityService.list(queryWrapper);

        // 导出配置
        ExportParams exportParams = new ExportParams("OD密度IPQC记录", "OD密度记录");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, DfOrtFqcOpticalDensity.class, list);

        // 设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("OD密度IPQC记录.xlsx", "UTF-8"));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");

        // 写出 Excel
        workbook.write(response.getOutputStream());
        workbook.close();
    }


    @GetMapping("/exportDfOrtOpticalDensityResult")
    @ApiOperation(value = "导出OD密度OQC结果及明细Excel")
    public void exportDfOrtOpticalDensityResult(
            @RequestParam(required = false) String batch,
            @RequestParam(required = false) String project,
            @RequestParam(required = false) String color,
            HttpServletResponse response)
             throws IOException {

        QueryWrapper<DfOrtOpticalDensityResult> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);
        }
        if (StringUtils.isNotBlank(project)) {
            queryWrapper.like("project", project);
        }
        if (StringUtils.isNotBlank(color)) {
            queryWrapper.like("color", color);
        }

        List<DfOrtOpticalDensityResult> resultList = dfOrtOpticalDensityResultService.list(queryWrapper);

        for (DfOrtOpticalDensityResult dfOrtOpticalDensityResult : resultList) {
            QueryWrapper<DfOrtOpticalDensity> detailWrapper = new QueryWrapper<>();
            detailWrapper.eq("batch", dfOrtOpticalDensityResult.getBatch());
            detailWrapper.eq("project", dfOrtOpticalDensityResult.getProject());
            detailWrapper.eq("color", dfOrtOpticalDensityResult.getColor());

            List<DfOrtOpticalDensity> list = dfOrtOpticalDensityService.list(detailWrapper);
            dfOrtOpticalDensityResult.setDfOrtOpticalDensityList(list);
        }

        // 导出Excel
        ExportParams exportParams = new ExportParams("OD密度OQC结果导出", "OD密度OQC", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, DfOrtOpticalDensityResult.class, resultList);

        // 设置响应头
        response.setHeader("content-type", "application/octet-stream");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("OD密度OQC结果导出.xls", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

        // 输出流写出
        workbook.write(response.getOutputStream());
        workbook.close();
    }



}
