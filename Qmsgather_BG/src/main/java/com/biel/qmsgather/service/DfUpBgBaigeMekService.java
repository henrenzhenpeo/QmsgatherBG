package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgBaigeMek;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_baige_mek(bg百格 mek)】的数据库操作Service
* @createDate 2024-12-18 17:29:55
*/
public interface DfUpBgBaigeMekService extends IService<DfUpBgBaigeMek> {

    String getMaxBatchId();

}
