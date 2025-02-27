package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgSandBlast;
import com.baomidou.mybatisplus.extension.service.IService;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import org.springframework.web.multipart.MultipartFile;

/**
* @author dafenqi
* @description 针对表【df_up_bg_sand_blast(bg喷砂)】的数据库操作Service
* @createDate 2024-12-12 15:16:27
*/
public interface DfUpBgSandBlastService extends IService<DfUpBgSandBlast> {



    String getMaxBatchId();




    boolean saveExcelWithJson(MultipartFile file, DfUpBgExcelDto baseInfo);









}
