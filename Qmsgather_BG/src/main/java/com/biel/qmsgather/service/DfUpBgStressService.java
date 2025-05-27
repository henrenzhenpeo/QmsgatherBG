package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgStress;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_stress(bg 应力)】的数据库操作Service
* @createDate 2025-05-26 17:40:38
*/
public interface DfUpBgStressService extends IService<DfUpBgStress> {
    String getMaxBatchId();
}
