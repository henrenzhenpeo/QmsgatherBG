package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgDrip;
import com.baomidou.mybatisplus.extension.service.IService;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import org.springframework.web.multipart.MultipartFile;

/**
* @author dafenqi
* @description 针对表【df_up_bg_drip(bg水滴角)】的数据库操作Service
* @createDate 2024-12-11 10:56:40
*/
public interface DfUpBgDripService extends IService<DfUpBgDrip> {
    String getMaxBatchId();


    boolean saveExcelWithJson(MultipartFile file, DfUpBgExcelDto baseInfo);




}
