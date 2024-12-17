package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgInkThickness;
import com.biel.qmsgather.service.DfUpBgInkThicknessService;
import com.biel.qmsgather.mapper.DfUpBgInkThicknessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_thickness(bg油墨厚度)】的数据库操作Service实现
* @createDate 2024-12-16 15:49:19
*/
@Service
@Transactional
public class DfUpBgInkThicknessServiceImpl extends ServiceImpl<DfUpBgInkThicknessMapper, DfUpBgInkThickness>
    implements DfUpBgInkThicknessService{

    @Autowired
    private DfUpBgInkThicknessMapper dfUpBgInkThicknessMapper;

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
        String maxBatchId = dfUpBgInkThicknessMapper.getMaxBatchId();
        String newBatchId = generateBatchId(maxBatchId);
        return newBatchId;
    }
}




