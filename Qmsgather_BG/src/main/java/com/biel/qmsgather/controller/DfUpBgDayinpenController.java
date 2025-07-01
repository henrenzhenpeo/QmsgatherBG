package com.biel.qmsgather.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgDayinpen;
import com.biel.qmsgather.service.DfUpBgDayinpenService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgDayinpen")
@CrossOrigin
@Api(tags = "BG 达音笔接口")
public class DfUpBgDayinpenController {

    @Autowired
    private DfUpBgDayinpenService dfUpBgDayinpenService;

    @PostMapping("/upload")
    @ApiOperation(value = "bg 达音笔接口上传")
    public Result uploadDfUpBgDayinpen(@RequestBody List<DfUpBgDayinpen> dfUpBgDayinpensList){

        String newBatchId = dfUpBgDayinpenService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgDayinpen dfUpBgDayinpen : dfUpBgDayinpensList) {
            dfUpBgDayinpen.setBatchId(dfUpBgDayinpen.getProcess()+"-"+newBatchId);
        }

        boolean b = dfUpBgDayinpenService.saveBatch(dfUpBgDayinpensList);
        if (b) {
            return new Result(200,"bg 达音笔接口上传成功");
        }

        return new Result(500,"bg 达音笔接口上传失败");
    }


    @GetMapping("/findDfUpBgDayinpen")
    @ApiOperation(value = "bg达音笔查询接口")
    public R findDfUpBgDayinpen(
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgDayinpen> dfUpBgDayinpenQueryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgDayinpenQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgDayinpenQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgDayinpenQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgDayinpenQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgDayinpenQueryWrapper.between("date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgDayinpen> pageResult = dfUpBgDayinpenService.page(
                new Page<>(page, limit),
                dfUpBgDayinpenQueryWrapper
        );

        return R.ok(pageResult);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据ID查询达音笔数据")
    public Result getDfUpBgDayinpenById(@PathVariable Long id) {
        DfUpBgDayinpen data = dfUpBgDayinpenService.getById(id);
        if (data != null) {
            return new Result(200, "查询成功", data);
        }
        return new Result(404, "未找到对应记录");
    }

    @PutMapping("/update")
    @ApiOperation(value = "根据ID修改达音笔数据")
    public Result updateDfUpBgDayinpen(@RequestBody DfUpBgDayinpen dfUpBgDayinpen) {
        if (dfUpBgDayinpen.getId() == null) {
            return new Result(400, "ID不能为空，无法定位修改记录");
        }
        boolean result = dfUpBgDayinpenService.updateById(dfUpBgDayinpen);
        return result ? new Result(200, "修改成功") : new Result(500, "修改失败");
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "根据ID批量删除达音笔数据")
    public Result deleteDfUpBgDayinpen(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new Result(400, "ID列表不能为空");
        }
        boolean result = dfUpBgDayinpenService.removeByIds(ids);
        return result ? new Result(200, "删除成功") : new Result(500, "删除失败");
    }


    @GetMapping("/exportDfUpBgDayinpen")
    @ApiOperation(value = "导出BG达音笔数据（可按工厂/阶段/项目/颜色）")
    public void exportDfUpBgDayinpen(
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            HttpServletResponse response
    ) throws IOException {
        // 查询条件
        QueryWrapper<DfUpBgDayinpen> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(factory)) {
            queryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            queryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            queryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            queryWrapper.eq("color", color);
        }
        queryWrapper.orderByDesc("date");

        // 查询数据
        List<DfUpBgDayinpen> list = dfUpBgDayinpenService.list(queryWrapper);

        // 导出配置
        ExportParams exportParams = new ExportParams("BG达音笔数据", "达音笔记录");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, DfUpBgDayinpen.class, list);

        // 设置响应头
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("BG达音笔数据.xlsx", "UTF-8"));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");

        // 写出
        workbook.write(response.getOutputStream());
        workbook.close();
    }


}
