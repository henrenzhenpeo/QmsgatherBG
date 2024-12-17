package com.biel.qmsgather.service;

import com.biel.qmsgather.domain.DfUpBgZhongkaoBack;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dafenqi
* @description 针对表【df_up_bg_zhongkao_back(bg终烤后)】的数据库操作Service
* @createDate 2024-12-12 15:16:35
*/
public interface DfUpBgZhongkaoBackService extends IService<DfUpBgZhongkaoBack> {
    String getMaxBatchId();
}
