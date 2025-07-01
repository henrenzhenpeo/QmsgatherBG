package com.biel.qmsgather.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfOrtGrAininessDetail;
import com.biel.qmsgather.domain.DfOrtGrAininessResult;
import com.biel.qmsgather.service.DfOrtGrAininessDetailService;
import com.biel.qmsgather.service.DfOrtGrAininessResultService;
import com.biel.qmsgather.util.DateUtil;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

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


    @GetMapping("/getDfOrtGrAininessPage")
    @ApiOperation(value = "分页查询bg颗粒度（含明细，可按批次号筛选）")
    public Result getDfOrtGrAininessPage(
            @RequestParam int pageNo,
            @RequestParam int pageSize,
            @RequestParam(required = false) String batch
    ) {
        QueryWrapper<DfOrtGrAininessResult> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);
        }

        IPage<DfOrtGrAininessResult> resultPage = dfOrtGrAininessResultService.page(
                new Page<>(pageNo, pageSize),
                queryWrapper
        );

        // 查询明细并组装
        for (DfOrtGrAininessResult result : resultPage.getRecords()) {
            List<DfOrtGrAininessDetail> detailList = dfOrtGrAininessDetailService.list(
                    new QueryWrapper<DfOrtGrAininessDetail>()
                            .eq("batch", result.getBatch())
            );
            result.setDfOrtGrAininessDetailList(detailList);
        }

        return new Result(200, "分页查询成功", resultPage);
    }

    @GetMapping("/getDfOrtGrAininessById")
    @ApiOperation(value = "根据ID查询bg颗粒度（含明细）")
    public Result getDfOrtGrAininessById(@RequestParam Long id) {
        if (id == null) {
            return new Result(400, "ID不能为空");
        }

        // 查询主表
        DfOrtGrAininessResult grAininessResult = dfOrtGrAininessResultService.getById(id);
        if (grAininessResult == null) {
            return new Result(404, "未找到对应数据");
        }

        // 查询明细
        List<DfOrtGrAininessDetail> detailList = dfOrtGrAininessDetailService.list(
                new QueryWrapper<DfOrtGrAininessDetail>().eq("batch", grAininessResult.getBatch())
        );
        grAininessResult.setDfOrtGrAininessDetailList(detailList);

        return new Result(200, "查询成功", grAininessResult);
    }


    @PostMapping("/updateDfOrtGrAininess")
    @ApiOperation(value = "修改bg颗粒度（含明细）")
    public Result updateDfOrtGrAininess(@RequestBody DfOrtGrAininessResult dfOrtGrAininessResult) {
        if (dfOrtGrAininessResult.getId() == null) {
            return new Result(400, "ID不能为空，无法修改");
        }

        boolean updateResult = dfOrtGrAininessResultService.updateById(dfOrtGrAininessResult);
        if (!updateResult) {
            return new Result(500, "修改主表失败");
        }

        // 删除旧明细
        dfOrtGrAininessDetailService.remove(
                new QueryWrapper<DfOrtGrAininessDetail>().eq("batch", dfOrtGrAininessResult.getBatch())
        );

        // 插入新明细
        for (DfOrtGrAininessDetail detail : dfOrtGrAininessResult.getDfOrtGrAininessDetailList()) {
            detail.setBatch(dfOrtGrAininessResult.getBatch());
        }
        dfOrtGrAininessDetailService.saveBatch(dfOrtGrAininessResult.getDfOrtGrAininessDetailList());

        return new Result(200, "修改成功");
    }


    @PostMapping("/deleteDfOrtGrAininess")
    @ApiOperation(value = "删除bg颗粒度（真删除，支持单个和批量）")
    public Result deleteDfOrtGrAininess(@RequestBody List<String> batchList) {
        if (batchList == null || batchList.isEmpty()) {
            return new Result(400, "批次号列表不能为空");
        }

        boolean resultDeleted = dfOrtGrAininessResultService.remove(
                new QueryWrapper<DfOrtGrAininessResult>().in("batch", batchList)
        );

        boolean detailDeleted = dfOrtGrAininessDetailService.remove(
                new QueryWrapper<DfOrtGrAininessDetail>().in("batch", batchList)
        );

        if (resultDeleted || detailDeleted) {
            return new Result(200, "删除成功");
        }
        return new Result(500, "删除失败");
    }


    @GetMapping("/exportDfOrtGrAininess")
    @ApiOperation(value = "导出BG颗粒度数据（含明细，可按批次/项目/颜色/工序筛选）")
    public void exportDfOrtGrAininess(
            @RequestParam(required = false) String batch,
            @RequestParam(required = false) String project,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String process,
            HttpServletResponse response
    ) throws IOException {
        // 主表条件
        QueryWrapper<DfOrtGrAininessResult> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);
        }
        if (StringUtils.isNotBlank(project)) {
            queryWrapper.like("project", project);
        }
        if (StringUtils.isNotBlank(color)) {
            queryWrapper.like("color", color);
        }
        if (StringUtils.isNotBlank(process)) {
            queryWrapper.like("process", process);
        }
        queryWrapper.orderByDesc("create_time");

        // 查询主表
        List<DfOrtGrAininessResult> resultList = dfOrtGrAininessResultService.list(queryWrapper);

        // 查询并绑定明细
        for (DfOrtGrAininessResult result : resultList) {
            QueryWrapper<DfOrtGrAininessDetail> detailWrapper = new QueryWrapper<>();
            detailWrapper.eq("batch", result.getBatch());
            detailWrapper.eq("project", result.getProject());
            detailWrapper.eq("color", result.getColor());
            detailWrapper.eq("process", result.getProcess());

            List<DfOrtGrAininessDetail> detailList = dfOrtGrAininessDetailService.list(detailWrapper);
            result.setDfOrtGrAininessDetailList(detailList);
        }

        // 导出
        ExportParams exportParams = new ExportParams("BG颗粒度数据", "颗粒度记录");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, DfOrtGrAininessResult.class, resultList);

        // 响应输出
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("BG颗粒度数据.xlsx", "UTF-8"));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        workbook.write(response.getOutputStream());
        workbook.close();
    }


}
