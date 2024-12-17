package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgLiquidThrowing;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_liquid_throwing(bg 液抛)】的数据库操作Service
* @createDate 2024-12-12 15:16:23
*/
public interface DfUpBgLiquidThrowingService extends IService<DfUpBgLiquidThrowing> {
    String getMaxBatchId();
}
