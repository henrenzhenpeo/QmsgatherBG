package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgAsback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_asback(bg AS后)】的数据库操作Mapper
* @createDate 2024-12-12 15:15:29
* @Entity com.biel.qmsgather.domain.DfUpBgAsback
*/
@Mapper
public interface DfUpBgAsbackMapper extends BaseMapper<DfUpBgAsback> {
    @Select("SELECT batch_id FROM df_up_bg_asback WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




