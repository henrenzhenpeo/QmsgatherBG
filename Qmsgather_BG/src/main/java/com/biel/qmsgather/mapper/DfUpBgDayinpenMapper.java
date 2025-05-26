package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgDayinpen;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_dayinpen(达音笔)】的数据库操作Mapper
* @createDate 2025-05-20 16:29:12
* @Entity com.biel.qmsgather.domain.DfUpBgDayinpen
*/
@Mapper
public interface DfUpBgDayinpenMapper extends BaseMapper<DfUpBgDayinpen> {

    @Select("SELECT batch_id FROM df_up_bg_dayinpen WHERE DATE(date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();

}




