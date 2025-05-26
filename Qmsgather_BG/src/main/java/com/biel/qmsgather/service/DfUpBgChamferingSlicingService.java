package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgChamferingSlicing;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_chamfering_slicing(bg倒角切片)】的数据库操作Service
* @createDate 2025-05-26 15:16:46
*/
public interface DfUpBgChamferingSlicingService extends IService<DfUpBgChamferingSlicing> {
    String getMaxBatchId();
}
