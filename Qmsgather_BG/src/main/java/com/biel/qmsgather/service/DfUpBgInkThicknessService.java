package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgInkThickness;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_thickness(bg油墨厚度)】的数据库操作Service
* @createDate 2024-12-16 15:49:19
*/
public interface DfUpBgInkThicknessService extends IService<DfUpBgInkThickness> {

    String getMaxBatchId();
}
