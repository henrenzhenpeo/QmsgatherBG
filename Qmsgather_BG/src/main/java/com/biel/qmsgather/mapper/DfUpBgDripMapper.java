package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgDrip;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_drip(bg水滴角)】的数据库操作Mapper
* @createDate 2024-12-11 10:56:40
* @Entity com.biel.qmsgather.domain.DfUpBgDrip
*/
@Mapper
public interface DfUpBgDripMapper extends BaseMapper<DfUpBgDrip> {

    @Select("SELECT batch_id FROM df_up_bg_drip WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




