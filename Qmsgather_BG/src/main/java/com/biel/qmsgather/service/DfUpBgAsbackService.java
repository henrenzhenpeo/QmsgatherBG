package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgAsback;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_asback(bg AS后)】的数据库操作Service
* @createDate 2024-12-12 15:15:29
*/
public interface DfUpBgAsbackService extends IService<DfUpBgAsback> {
    String getMaxBatchId();
}
