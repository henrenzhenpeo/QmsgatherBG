package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgGrindingBottom;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_grinding_bottom(bg 磨底)】的数据库操作Service
* @createDate 2024-12-12 15:15:34
*/
public interface DfUpBgGrindingBottomService extends IService<DfUpBgGrindingBottom> {
    String getMaxBatchId();
}
