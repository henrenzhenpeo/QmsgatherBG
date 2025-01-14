package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgBaigeTestImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_baige_test_img(bg百格测试图片上传)】的数据库操作Mapper
* @createDate 2024-12-24 10:47:58
* @Entity com.biel.qmsgather.domain.DfUpBgBaigeTestImg
*/
@Mapper
public interface DfUpBgBaigeTestImgMapper extends BaseMapper<DfUpBgBaigeTestImg> {

    @Select("SELECT batch_id FROM df_up_bg_baige_test_img WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




