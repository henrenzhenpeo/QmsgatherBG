package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgBaigeMek;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_baige_mek(bg百格 mek)】的数据库操作Mapper
* @createDate 2024-12-18 17:29:55
* @Entity com.biel.qmsgather.domain.DfUpBgBaigeMek
*/
@Mapper
public interface DfUpBgBaigeMekMapper extends BaseMapper<DfUpBgBaigeMek> {

    @Select("SELECT batch_id FROM df_up_bg_baige_mek WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




