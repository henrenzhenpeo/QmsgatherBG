package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.biel.qmsgather.domain.DfUpBgSandBlast;
import com.biel.qmsgather.domain.DfUpBgStress;
import com.biel.qmsgather.service.DfUpBgStressService;
import com.biel.qmsgather.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bg/dfUpBgStress")
@CrossOrigin
@Api(tags = "bg应力接口")
public class DfUpBgStressController {

    @Autowired
    private DfUpBgStressService dfUpBgStressService;

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


}
