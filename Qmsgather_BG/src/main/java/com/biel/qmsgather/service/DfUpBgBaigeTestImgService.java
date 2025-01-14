package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgBaigeTestImg;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_baige_test_img(bg百格测试图片上传)】的数据库操作Service
* @createDate 2024-12-24 10:47:58
*/
public interface DfUpBgBaigeTestImgService extends IService<DfUpBgBaigeTestImg> {
    String getMaxBatchId();
}
