package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.biel.qmsgather.domain.DfUpBgInkThickness;
import com.biel.qmsgather.domain.DfUpBgSandBlast;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.domain.dto.DfUpBgInkThicknessDto;
import com.biel.qmsgather.domain.dto.DfUpBgSandBlastDto;
import com.biel.qmsgather.service.DfUpBgSandBlastService;
import com.biel.qmsgather.util.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgSandBlast")
@CrossOrigin
@Api(tags = "bg喷砂接口")
public class DfUpBgSandBlastController {


    @Autowired
    private DfUpBgSandBlastService dfUpBgInkLandHeightList;

    @PostMapping("/upload")
    @ApiOperation(value = "bg喷砂接口上传")
    public Result uploadDfUpBgSandBlast(@RequestBody List<DfUpBgSandBlast> dfUpBgSandBlastList){

        String newBatchId = dfUpBgInkLandHeightList.getMaxBatchId();
        System.out.println("newBatchId"+newBatchId);

        for (DfUpBgSandBlast dfUpBgSandBlast : dfUpBgSandBlastList) {
            dfUpBgSandBlast.setBatchId(dfUpBgSandBlast.getProcess()+"-"+newBatchId);
        }

        boolean b = dfUpBgInkLandHeightList.saveBatch(dfUpBgSandBlastList);
//        Boolean b = dfUpCgResistanceService.save(dfUpCgResistanceList);
        if (b) {
            return new Result(200,"bg喷砂接口上传成功");
        }

        return new Result(500,"bg喷砂接口上传失败");
    }


    @GetMapping("/findDfUpBgSandBlast")
    @ApiOperation(value = "bg喷砂查询接口")
    public R findDfUpBgSandBlast(
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
        QueryWrapper<DfUpBgSandBlast> dfUpBgSandBlastQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfUpBgSandBlastQueryWrapper.eq("process", process);
        }
        if (StringUtils.isNotEmpty(factory)) {
            dfUpBgSandBlastQueryWrapper.eq("factory", factory);
        }
        if (StringUtils.isNotEmpty(stage)) {
            dfUpBgSandBlastQueryWrapper.eq("stage", stage);
        }
        if (StringUtils.isNotEmpty(project)) {
            dfUpBgSandBlastQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfUpBgSandBlastQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(startTestDate) && StringUtils.isNotEmpty(endTestDate)) {
            dfUpBgSandBlastQueryWrapper.between("test_date", startTestDate, endTestDate);
        }

        // 执行分页查询
        IPage<DfUpBgSandBlast> pageResult = dfUpBgInkLandHeightList.page(
                new Page<>(page, limit),
                dfUpBgSandBlastQueryWrapper
        );

        return R.ok(pageResult);
    }




    @PostMapping("/uploadExcelWithJson")
    @ApiOperation(value = "bg 喷砂接口上传Excel和JSON数据")
    public Result uploadExcelWithJson(@RequestParam("file") MultipartFile file,
                                      @RequestParam("jsonData") String jsonData) {
        try {
            // 解析JSON数据
            DfUpBgExcelDto baseInfo = new ObjectMapper().readValue(jsonData, DfUpBgExcelDto.class);

            // 调用服务处理Excel和JSON数据
            boolean result = dfUpBgInkLandHeightList.saveExcelWithJson(file, baseInfo);

            if (result) {
                return new Result(200, "bg 喷砂Excel和JSON数据上传成功");
            } else {
                return new Result(500, "bg 喷砂Excel和JSON数据上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500, "bg 液抛Excel和JSON数据上传失败: " + e.getMessage());
        }
    }













}
