package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgStress;
import com.biel.qmsgather.service.DfUpBgStressService;
import com.biel.qmsgather.mapper.DfUpBgStressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author dafenqi
* @description 针对表【df_up_bg_stress(bg 应力)】的数据库操作Service实现
* @createDate 2025-05-26 17:40:38
*/
@Service
public class DfUpBgStressServiceImpl extends ServiceImpl<DfUpBgStressMapper, DfUpBgStress>
    implements DfUpBgStressService{

    @Autowired
    private DfUpBgStressMapper dfUpBgStressMapper;

    private String generateBatchId(String maxBatchId) {
        // 获取当天日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());

        // 如果没有批次号，直接从 1 开始
        int newCount = 1;
        if (maxBatchId != null && maxBatchId.contains("+")) {
            String[] parts = maxBatchId.split("\\+");
            newCount = Integer.parseInt(parts[1]) + 1;
        }

        return today + "+" + newCount;
    }

    @Override
    public String getMaxBatchId() {
        String maxBatchId = dfUpBgStressMapper.getMaxBatchId();
        // 生成新的批次号
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }
}




