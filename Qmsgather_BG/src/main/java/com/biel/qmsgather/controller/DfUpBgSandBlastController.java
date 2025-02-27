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


    @PostMapping("/findDfUpBgSandBlast")
    @ApiOperation(value = "bg喷砂查询接口")
    public R findDfUpBgSandBlast(@RequestBody DfUpBgSandBlastDto dfUpBgSandBlastDto) {
        QueryWrapper<DfUpBgSandBlast> dfUpBgSandBlastQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(dfUpBgSandBlastDto.getProcess())) {
            dfUpBgSandBlastQueryWrapper.eq("process", dfUpBgSandBlastDto.getProcess());
        }
        if (StringUtils.isNotEmpty(dfUpBgSandBlastDto.getFactory())) {
            dfUpBgSandBlastQueryWrapper.eq("factory", dfUpBgSandBlastDto.getFactory());
        }
        if (StringUtils.isNotEmpty(dfUpBgSandBlastDto.getStage())) {
            dfUpBgSandBlastQueryWrapper.eq("stage", dfUpBgSandBlastDto.getStage());
        }
        if (StringUtils.isNotEmpty(dfUpBgSandBlastDto.getProject())) {
            dfUpBgSandBlastQueryWrapper.eq("project", dfUpBgSandBlastDto.getProject());
        }
        if (StringUtils.isNotEmpty(dfUpBgSandBlastDto.getColor())) {
            dfUpBgSandBlastQueryWrapper.eq("color", dfUpBgSandBlastDto.getColor());
        }
        dfUpBgSandBlastQueryWrapper.between("test_date",dfUpBgSandBlastDto.getStartTestDate(),dfUpBgSandBlastDto.getEndTestDate());

        IPage<DfUpBgSandBlast> page = dfUpBgInkLandHeightList.page(new Page<>(dfUpBgSandBlastDto.getPageIndex(), dfUpBgSandBlastDto.getPageSize()), dfUpBgSandBlastQueryWrapper);
        return R.ok(page);
    }




    @PostMapping("/uploadExcelWithJson")
    @ApiOperation(value = "bg 液抛接口上传Excel和JSON数据")
    public Result uploadExcelWithJson(@RequestParam("file") MultipartFile file,
                                      @RequestParam("jsonData") String jsonData) {
        try {
            // 解析JSON数据
            DfUpBgExcelDto baseInfo = new ObjectMapper().readValue(jsonData, DfUpBgExcelDto.class);

            // 调用服务处理Excel和JSON数据
            boolean result = dfUpBgInkLandHeightList.saveExcelWithJson(file, baseInfo);

            if (result) {
                return new Result(200, "bg 液抛Excel和JSON数据上传成功");
            } else {
                return new Result(500, "bg 液抛Excel和JSON数据上传失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500, "bg 液抛Excel和JSON数据上传失败: " + e.getMessage());
        }
    }













}
