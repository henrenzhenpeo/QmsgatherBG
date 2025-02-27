package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgBaigeTestImg;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
* @author dafenqi
* @description 针对表【df_up_bg_baige_test_img(bg百格测试图片上传)】的数据库操作Service
* @createDate 2024-12-24 10:47:58
*/
public interface DfUpBgBaigeTestImgService extends IService<DfUpBgBaigeTestImg> {
    String getMaxBatchId();


    public Map<String, Object> extractAndSaveImagesFromExcel(MultipartFile excelFile, String process, String testDate) throws IOException;
}
