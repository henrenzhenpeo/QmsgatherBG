package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgAsback;
import com.biel.qmsgather.service.DfUpBgAsbackService;
import com.biel.qmsgather.mapper.DfUpBgAsbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author dafenqi
* @description 针对表【df_up_bg_asback(bg AS后)】的数据库操作Service实现
* @createDate 2024-12-12 15:15:29
*/
@Service
@Transactional
public class DfUpBgAsbackServiceImpl extends ServiceImpl<DfUpBgAsbackMapper, DfUpBgAsback>
    implements DfUpBgAsbackService{

    @Autowired
    private DfUpBgAsbackMapper dfUpBgAsbackMapper;


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

        String maxBatchId = dfUpBgAsbackMapper.getMaxBatchId();
        // 生成新的批次号
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }
}




