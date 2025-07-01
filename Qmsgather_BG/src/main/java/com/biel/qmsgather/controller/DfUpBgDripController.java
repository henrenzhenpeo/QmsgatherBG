package com.biel.qmsgather.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfOrtOilInk;
import com.biel.qmsgather.domain.DfUpBgDrip;
import com.biel.qmsgather.service.DfOrtOilInkService;
import com.biel.qmsgather.service.DfUpBgDripService;
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
import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgDrip")
@CrossOrigin
@Api(tags = "水滴角接口")
public class DfUpBgDripController {

    @Autowired
    private DfUpBgDripService dfUpBgDripService;

    @Autowired
    private DfOrtOilInkService dfOrtOilInkService;


    @PostMapping("/uploadDfOrtOilInkService")
    @ApiOperation(value = "水滴角接口上传")
    public Result uploadDfOrtOilInkService(@RequestBody List<DfOrtOilInk> dfOrtOilInkList){
        for (DfOrtOilInk dfOrtOilInk : dfOrtOilInkList) {
            System.out.println(dfOrtOilInk.getCheckTime());
            String batchFromDate = DateUtil.getBatchFromDate(dfOrtOilInk.getCheckTime());
            dfOrtOilInk.setBatch(batchFromDate);
            if (dfOrtOilInk.getDayOrNight().equals("白斑")) {
                dfOrtOilInk.setDayOrNight("A");
            }else if (dfOrtOilInk.getDayOrNight().equals("晚班")) {
                dfOrtOilInk.setDayOrNight("B");
            }
        }

        boolean b = dfOrtOilInkService.saveBatch(dfOrtOilInkList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"水滴角接口上传成功");
        }

        return new Result(500,"水滴角接口上传失败");
    }


    @GetMapping("/getDfOrtOilInkPage")
    @ApiOperation(value = "分页查询水滴角记录（可按batch筛选）")
    public Result getDfOrtOilInkPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String batch) {

        QueryWrapper<DfOrtOilInk> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);
        }
        // 执行分页查询
        IPage<DfOrtOilInk> pageResult = dfOrtOilInkService.page(
                new Page<>(pageNum, pageSize),
                queryWrapper
        );

        return new Result(200, "查询成功", pageResult);
    }

    @GetMapping("/getDfOrtOilInkById/{id}")
    @ApiOperation(value = "根据ID查询水滴角记录")
    public Result getDfOrtOilInkById(@PathVariable Long id) {
        DfOrtOilInk result = dfOrtOilInkService.getById(id);
        if (result == null) {
            return new Result(404, "记录不存在");
        }
        return new Result(200, "查询成功", result);
    }

    @PutMapping("/updateDfOrtOilInk")
    @ApiOperation(value = "根据ID修改水滴角记录")
    public Result updateDfOrtOilInk(@RequestBody DfOrtOilInk dfOrtOilInk) {
        if (dfOrtOilInk.getId() == null) {
            return new Result(400, "ID不能为空，无法定位修改记录");
        }

        // 重新生成批次号
        String batchFromDate = DateUtil.getBatchFromDate(dfOrtOilInk.getCheckTime());
        dfOrtOilInk.setBatch(batchFromDate);

        if ("白班".equals(dfOrtOilInk.getDayOrNight())) {
            dfOrtOilInk.setDayOrNight("A");
        } else if ("晚班".equals(dfOrtOilInk.getDayOrNight())) {
            dfOrtOilInk.setDayOrNight("B");
        }

        boolean updateResult = dfOrtOilInkService.updateById(dfOrtOilInk);
        return updateResult ? new Result(200, "修改成功") : new Result(500, "修改失败");
    }

    @DeleteMapping("/deleteDfOrtOilInkBatch")
    @ApiOperation(value = "批量删除水滴角记录")
    public Result deleteDfOrtOilInkBatch(@RequestBody List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new Result(400, "ID列表不能为空");
        }

        boolean removeResult = dfOrtOilInkService.removeByIds(ids);
        return removeResult ? new Result(200, "批量删除成功") : new Result(500, "批量删除失败");
    }



    @PostMapping("/upload")
    @ApiOperation(value = "水滴角接口上传")
    public Result uploadDfUpBgInkDensity(@RequestBody List<DfUpBgDrip> dfUpBgDripList){

        String newBatchId = dfUpBgDripService.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgDrip dfUpBgDrip : dfUpBgDripList) {
            dfUpBgDrip.setBatchId(dfUpBgDrip.getProcess()+"-"+newBatchId);
        }

        boolean b = dfUpBgDripService.saveBatch(dfUpBgDripList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"水滴角接口上传成功");
        }

        return new Result(500,"水滴角接口上传失败");
    }


    @GetMapping("/findDfUpBgDrip")
    @ApiOperation(value = "水滴角查询接口")
    public R findDfUpBgDrip(
            @RequestParam(value = "process", required = false) String process,
            @RequestParam(value = "factory", required = false) String factory,
            @RequestParam(value = "stage", required = false) String stage,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "startTestDate", required = false) String startTestDate,
            @RequestParam(value = "endTestDate", required = false) String endTestDate,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {

        // 创建查询条件
        QueryWrapper<DfUpBgDrip> dfUpBgDripQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfUpBgDripQueryWrapper.eq("process", process);
        }
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgDripQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgDripQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgDripQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgDripQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgDripQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 分页查询
        IPage<DfUpBgDrip> pageResult = dfUpBgDripService.page(
                new Page<>(page, limit),
                dfUpBgDripQueryWrapper
        );

        return R.ok(pageResult);
    }

    @GetMapping("/exportDfOrtOilInk")
    @ApiOperation(value = "导出水滴角数据（按batch筛选）")
    public void exportDfOrtOilInk(
            @RequestParam(required = false) String batch,
            HttpServletResponse response) throws IOException {

        QueryWrapper<DfOrtOilInk> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(batch)) {
            queryWrapper.like("batch", batch);
        }
        List<DfOrtOilInk> list = dfOrtOilInkService.list(queryWrapper);

        // 导出文件名
        String fileName = "水滴角数据_" + (StringUtils.isNotBlank(batch) ? batch : "全部") + ".xlsx";

        // 导出参数
        ExportParams params = new ExportParams("水滴角数据", "数据", ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, DfOrtOilInk.class, list);

        // 设置响应头
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

        // 写出流
        workbook.write(response.getOutputStream());
        workbook.close();
    }

}
