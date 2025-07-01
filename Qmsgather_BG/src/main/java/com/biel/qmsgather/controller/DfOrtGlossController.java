package com.biel.qmsgather.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfOrtGlossDetail;
import com.biel.qmsgather.domain.DfOrtGlossResult;
import org.apache.commons.lang3.StringUtils;
import com.biel.qmsgather.service.DfOrtGlossDetailService;
import com.biel.qmsgather.service.DfOrtGlossResultService;
import com.biel.qmsgather.util.DateUtil;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

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

    @GetMapping("/getDfOrtGlossPage")
    @ApiOperation(value = "分页查询bg光泽度（含明细，可按批次号筛选）")
    public Result getDfOrtGlossPage(
            @RequestParam int pageNo,
            @RequestParam int pageSize,
            @RequestParam(required = false) String batch
    ) {
        // 构建查询条件
        QueryWrapper<DfOrtGlossResult> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("is_deleted", 0);
        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);  // 模糊查询
        }

        // 分页查询主表
        IPage<DfOrtGlossResult> resultPage = dfOrtGlossResultService.page(
                new Page<>(pageNo, pageSize),
                queryWrapper
        );
        // 给每个主表对象查对应明细，组装一对多结构
        for (DfOrtGlossResult result : resultPage.getRecords()) {
            List<DfOrtGlossDetail> detailList = dfOrtGlossDetailService.list(
                    new QueryWrapper<DfOrtGlossDetail>()
                            .eq("batch", result.getBatch())
//                            .eq("is_deleted", 0)
            );
            result.setDfOrtGlossDetailList(detailList);
        }

        return new Result(200, "分页查询成功", resultPage);
    }

    @GetMapping("/getDfOrtGlossById")
    @ApiOperation(value = "根据ID查询bg光泽度（含明细）")
    public Result getDfOrtGlossById(@RequestParam Long id) {
        if (id == null) {
            return new Result(400, "ID不能为空");
        }

        DfOrtGlossResult glossResult = dfOrtGlossResultService.getById(id);
        if (glossResult == null) {
            return new Result(404, "未找到对应数据");
        }

        List<DfOrtGlossDetail> detailList = dfOrtGlossDetailService.list(
                new QueryWrapper<DfOrtGlossDetail>()
                        .eq("batch", glossResult.getBatch())
        );
        glossResult.setDfOrtGlossDetailList(detailList);

        return new Result(200, "查询成功", glossResult);
    }


    @PutMapping("/updateDfOrtGloss")
    @ApiOperation(value = "根据ID修改bg光泽度（含明细）")
    public Result updateDfOrtGloss(@RequestBody DfOrtGlossResult dfOrtGlossResult) {
        if (dfOrtGlossResult.getId() == null) {
            return new Result(400, "ID不能为空，无法定位修改记录");
        }

        // 先更新主表
        boolean updateResult = dfOrtGlossResultService.updateById(dfOrtGlossResult);
        if (!updateResult) {
            return new Result(500, "修改主表失败");
        }

        // 真删除原明细
        dfOrtGlossDetailService.remove(new QueryWrapper<DfOrtGlossDetail>().eq("batch", dfOrtGlossResult.getBatch()));

        // 批量新增新明细，确保批次号正确
        for (DfOrtGlossDetail detail : dfOrtGlossResult.getDfOrtGlossDetailList()) {
            detail.setBatch(dfOrtGlossResult.getBatch());
        }
        dfOrtGlossDetailService.saveBatch(dfOrtGlossResult.getDfOrtGlossDetailList());

        return new Result(200, "修改成功");
    }


    @PostMapping("/deleteDfOrtGloss")
    @ApiOperation(value = "删除bg光泽度（真删除，支持单个和批量）")
    public Result deleteDfOrtGloss(@RequestBody List<String> batchList) {
        if (batchList == null || batchList.isEmpty()) {
            return new Result(400, "批次号列表不能为空");
        }

        // 删除主表
        boolean resultDeleted = dfOrtGlossResultService.remove(
                new QueryWrapper<DfOrtGlossResult>().in("batch", batchList)
        );

        // 删除明细表
        boolean detailDeleted = dfOrtGlossDetailService.remove(
                new QueryWrapper<DfOrtGlossDetail>().in("batch", batchList)
        );

        if (resultDeleted || detailDeleted) {
            return new Result(200, "删除成功");
        }
        return new Result(500, "删除失败");
    }


    @GetMapping("/exportDfOrtGloss")
    @ApiOperation(value = "导出BG光泽度数据（含明细，按批次/项目/颜色/工序筛选）")
    public void exportDfOrtGloss(
            @RequestParam(required = false) String batch,
            @RequestParam(required = false) String project,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String process,
            HttpServletResponse response
    ) throws IOException {
        // 构建主表查询条件
        QueryWrapper<DfOrtGlossResult> queryWrapper = new QueryWrapper<>();
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
        List<DfOrtGlossResult> resultList = dfOrtGlossResultService.list(queryWrapper);

        // 查询明细并与主表逐个匹配筛选
        for (DfOrtGlossResult result : resultList) {
            QueryWrapper<DfOrtGlossDetail> detailWrapper = new QueryWrapper<>();
            detailWrapper.eq("batch", result.getBatch());
            detailWrapper.eq("project", result.getProject());
            detailWrapper.eq("color", result.getColor());
            detailWrapper.eq("process", result.getProcess());

            List<DfOrtGlossDetail> detailList = dfOrtGlossDetailService.list(detailWrapper);
            result.setDfOrtGlossDetailList(detailList);
        }

        // 导出
        ExportParams exportParams = new ExportParams("BG光泽度数据", "光泽度记录");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, DfOrtGlossResult.class, resultList);

        // 响应流输出
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("BG光泽度数据.xlsx", "UTF-8"));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        workbook.write(response.getOutputStream());
        workbook.close();
    }



}
