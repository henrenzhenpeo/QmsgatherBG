package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgDayinpen;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_dayinpen(达音笔)】的数据库操作Service
* @createDate 2025-05-20 16:29:12
*/
public interface DfUpBgDayinpenService extends IService<DfUpBgDayinpen> {

    String getMaxBatchId();

}
