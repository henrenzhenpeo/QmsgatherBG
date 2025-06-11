package com.biel.qmsgather.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.biel.qmsgather.domain.DfOrtStandardConfig;
import com.biel.qmsgather.service.DfOrtStandardConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bg/dfOrtStandardConfig")
@CrossOrigin
@Api(tags = "BG 标准值接口")
public class DfOrtStandardConfigController {

    @Autowired
    private DfOrtStandardConfigService dfOrtStandardConfigService;

    @GetMapping("/findDfOrtStandardConfig")
    @ApiOperation(value = "BG 标准值接口查询接口")
    public R findDfOrtStandardConfig(
            @RequestParam(value = "process", required = false) String process,
            @RequestParam(value = "project", required = false) String project,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "color", required = false) String color) {

        // 创建查询条件
        QueryWrapper<DfOrtStandardConfig> dfOrtStandardConfigQueryWrapper = new QueryWrapper<>();

        // 构建查询条件
        if (StringUtils.isNotEmpty(process)) {
            dfOrtStandardConfigQueryWrapper.like("process", process);
        }

        if (StringUtils.isNotEmpty(project)) {
            dfOrtStandardConfigQueryWrapper.eq("project", project);
        }
        if (StringUtils.isNotEmpty(color)) {
            dfOrtStandardConfigQueryWrapper.eq("color", color);
        }
        if (StringUtils.isNotEmpty(type)) {
            dfOrtStandardConfigQueryWrapper.eq("type", type);
        }

        // 执行分页查询
        List<DfOrtStandardConfig> configs = dfOrtStandardConfigService.list(dfOrtStandardConfigQueryWrapper);
        // 转成想要的 Map 结构
        Map<String, Map<String, Object>> result = configs.stream().collect(Collectors.toMap(
                config -> config.getStandardItem().toLowerCase(), // key 是 standardItem 小写
                config -> {
                    Map<String, Object> value = new HashMap<>();
                    value.put("standardMin", config.getStandardMin());
                    value.put("standardMax", config.getStandardMax());
                    return value;
                }
        ));
        return R.ok(result);
    }


}
