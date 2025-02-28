package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.baomidou.mybatisplus.extension.service.IService;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import org.springframework.web.multipart.MultipartFile;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_land_height(油墨厚度+机油高度)】的数据库操作Service
* @createDate 2024-12-10 17:31:21
*/
public interface DfUpBgInkLandHeightService extends IService<DfUpBgInkLandHeight> {
    String getMaxBatchId();



    boolean saveExcelWithJson(MultipartFile file, DfUpBgExcelDto baseInfo);
}
