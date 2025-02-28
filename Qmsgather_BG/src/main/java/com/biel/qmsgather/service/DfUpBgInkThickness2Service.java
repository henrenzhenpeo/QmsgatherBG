package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgInkThickness2;
import com.baomidou.mybatisplus.extension.service.IService;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import org.springframework.web.multipart.MultipartFile;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_thickness2(bg油墨厚度)】的数据库操作Service
* @createDate 2025-02-27 16:28:07
*/
public interface DfUpBgInkThickness2Service extends IService<DfUpBgInkThickness2> {
    String getMaxBatchId();


    boolean saveExcelWithJson(MultipartFile file, DfUpBgExcelDto baseInfo);


}
