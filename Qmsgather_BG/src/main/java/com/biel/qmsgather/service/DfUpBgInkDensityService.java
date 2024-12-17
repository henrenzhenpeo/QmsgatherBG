package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_density(油墨密度- OD)】的数据库操作Service
* @createDate 2024-12-10 17:31:13
*/
public interface DfUpBgInkDensityService extends IService<DfUpBgInkDensity> {
    String getMaxBatchId();
}
