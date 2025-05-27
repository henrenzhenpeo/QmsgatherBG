package com.biel.qmsgather.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biel.qmsgather.domain.DfUpBgStress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_stress(bg 应力)】的数据库操作Mapper
* @createDate 2025-05-26 17:40:38
* @Entity com.biel.qmsgather.domain.DfUpBgStress
*/
@Mapper
public interface DfUpBgStressMapper extends BaseMapper<DfUpBgStress> {
    @Select("SELECT batch_id FROM df_up_bg_stress WHERE DATE(date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




