package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgInkThickness2;
import com.biel.qmsgather.mapper.DfUpBgInkThicknessMapper;
import com.biel.qmsgather.service.DfUpBgInkThickness2Service;
import com.biel.qmsgather.mapper.DfUpBgInkThickness2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_thickness2(bg油墨厚度)】的数据库操作Service实现
* @createDate 2025-02-27 16:28:07
*/
@Service
@Transactional
public class DfUpBgInkThickness2ServiceImpl extends ServiceImpl<DfUpBgInkThickness2Mapper, DfUpBgInkThickness2>
    implements DfUpBgInkThickness2Service{
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




