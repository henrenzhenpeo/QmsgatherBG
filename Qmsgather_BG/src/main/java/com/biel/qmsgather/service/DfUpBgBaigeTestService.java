package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgBaigeTest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.biel.qmsgather.domain.dto.DfUpBgExcelDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* @author dafenqi
* @description 针对表【df_up_bg_baige_test(bg 百格测试/煮水百格测试)】的数据库操作Service
* @createDate 2024-12-18 09:05:37
*/
public interface DfUpBgBaigeTestService extends IService<DfUpBgBaigeTest> {

    String getMaxBatchId();

 int parseExcelFile(MultipartFile file, DfUpBgExcelDto baseInfo, String batchId) throws IOException;






}
