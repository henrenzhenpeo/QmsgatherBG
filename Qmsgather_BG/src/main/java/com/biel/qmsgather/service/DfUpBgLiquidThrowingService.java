package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgLiquidThrowing;
import com.baomidou.mybatisplus.extension.service.IService;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import com.biel.qmsgather.domain.dto.DfUpBgLiquidThrowingDto;
import org.springframework.web.multipart.MultipartFile;

/**
* @author dafenqi
* @description 针对表【df_up_bg_liquid_throwing(bg 液抛)】的数据库操作Service
* @createDate 2024-12-12 15:16:23
*/
public interface DfUpBgLiquidThrowingService extends IService<DfUpBgLiquidThrowing> {
    String getMaxBatchId();



    /**
     * 保存Excel和JSON数据
     * @param file Excel文件
     * @param baseInfo 基础信息DTO
     * @return 是否保存成功
     */
    boolean saveExcelWithJson(MultipartFile file, DfUpBgExcelDto baseInfo);


















}
