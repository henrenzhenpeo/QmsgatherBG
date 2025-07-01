package com.biel.qmsgather.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfOrtInkThickness;
import com.biel.qmsgather.service.DfOrtInkThicknessService;
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

    @GetMapping("/getDfOrtInkThicknessPage")
    @ApiOperation(value = "分页查询bg ort油墨厚度（可按批次号筛选）")
    public Result getDfOrtInkThicknessPage(
            @RequestParam int pageNo,
            @RequestParam int pageSize,
            @RequestParam(required = false) String batch
    ) {
        QueryWrapper<DfOrtInkThickness> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);
        }

        IPage<DfOrtInkThickness> resultPage = ortInkThicknessService.page(
                new Page<>(pageNo, pageSize),
                queryWrapper
        );
        return new Result(200, "分页查询成功", resultPage);
    }

    @GetMapping("/getDfOrtInkThicknessById")
    @ApiOperation(value = "根据ID查询bg ort油墨厚度")
    public Result getDfOrtInkThicknessById(@RequestParam Long id) {
        if (id == null) {
            return new Result(400, "ID不能为空");
        }
        DfOrtInkThickness record = ortInkThicknessService.getById(id);
        if (record == null) {
            return new Result(404, "未找到对应数据");
        }
        return new Result(200, "查询成功", record);
    }


    @PutMapping("/updateDfOrtInkThickness")
    @ApiOperation(value = "根据ID修改bg ort油墨厚度")
    public Result updateDfOrtInkThickness(@RequestBody DfOrtInkThickness dfOrtInkThickness) {
        if (dfOrtInkThickness.getId() == null) {
            return new Result(400, "ID不能为空，无法定位修改记录");
        }
        // 重新生成批次号
        String batchFromDate = DateUtil.getBatchFromDate(dfOrtInkThickness.getTestTime());
        dfOrtInkThickness.setBatch(batchFromDate);

        boolean updateResult = ortInkThicknessService.updateById(dfOrtInkThickness);
        if (updateResult) {
            return new Result(200, "修改成功");
        }
        return new Result(500, "修改失败");
    }


    @PostMapping("/deleteDfOrtInkThickness")
    @ApiOperation(value = "删除bg ort油墨厚度（真删除，支持单个和批量）")
    public Result deleteDfOrtInkThickness(@RequestBody List<String> batchList) {
        if (batchList == null || batchList.isEmpty()) {
            return new Result(400, "批次号列表不能为空");
        }

        boolean deleteResult = ortInkThicknessService.remove(
                new QueryWrapper<DfOrtInkThickness>().in("batch", batchList)
        );

        if (deleteResult) {
            return new Result(200, "删除成功");
        }
        return new Result(500, "删除失败");
    }


    @GetMapping("/exportDfOrtInkThickness")
    @ApiOperation(value = "导出BG ORT油墨厚度数据（可按批次/项目/颜色/工厂/阶段筛选）")
    public void exportDfOrtInkThickness(
            @RequestParam(required = false) String batch,
            @RequestParam(required = false) String project,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String factory,
            @RequestParam(required = false) String stage,
            HttpServletResponse response
    ) throws IOException {
        QueryWrapper<DfOrtInkThickness> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);
        }
        if (StringUtils.isNotBlank(project)) {
            queryWrapper.like("project", project);
        }
        if (StringUtils.isNotBlank(color)) {
            queryWrapper.like("color", color);
        }
        if (StringUtils.isNotBlank(factory)) {
            queryWrapper.like("factory", factory);
        }
        if (StringUtils.isNotBlank(stage)) {
            queryWrapper.like("stage", stage);
        }
        queryWrapper.orderByDesc("test_time");

        List<DfOrtInkThickness> list = ortInkThicknessService.list(queryWrapper);

        ExportParams exportParams = new ExportParams("BG ORT油墨厚度", "厚度记录");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, DfOrtInkThickness.class, list);

        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("BG油墨厚度数据.xlsx", "UTF-8"));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        workbook.write(response.getOutputStream());
        workbook.close();
    }


}
