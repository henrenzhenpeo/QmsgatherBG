package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgChamferingSlicing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_chamfering_slicing(bg倒角切片)】的数据库操作Mapper
* @createDate 2025-05-26 15:16:46
* @Entity com.biel.qmsgather.domain.DfUpBgChamferingSlicing
*/
@Mapper
public interface DfUpBgChamferingSlicingMapper extends BaseMapper<DfUpBgChamferingSlicing> {

    @Select("SELECT batch_id FROM df_up_bg_chamfering_slicing WHERE DATE(date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();

}




