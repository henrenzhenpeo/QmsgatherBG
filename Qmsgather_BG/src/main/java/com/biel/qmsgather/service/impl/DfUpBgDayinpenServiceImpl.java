package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgDayinpen;
import com.biel.qmsgather.service.DfUpBgDayinpenService;
import com.biel.qmsgather.mapper.DfUpBgDayinpenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author dafenqi
* @description 针对表【df_up_bg_dayinpen(达音笔)】的数据库操作Service实现
* @createDate 2025-05-20 16:29:12
*/
@Service
public class DfUpBgDayinpenServiceImpl extends ServiceImpl<DfUpBgDayinpenMapper, DfUpBgDayinpen>
    implements DfUpBgDayinpenService{

    @Autowired
    private DfUpBgDayinpenMapper dfUpBgDayinpenMapper;

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
        String maxBatchId = dfUpBgDayinpenMapper.getMaxBatchId();
        // 生成新的批次号
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }
}




