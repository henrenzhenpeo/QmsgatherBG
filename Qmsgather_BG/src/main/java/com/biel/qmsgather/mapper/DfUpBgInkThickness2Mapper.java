package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgInkThickness2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_thickness2(bg油墨厚度)】的数据库操作Mapper
* @createDate 2025-02-27 16:28:07
* @Entity com.biel.qmsgather.domain.DfUpBgInkThickness2
*/
@Mapper
public interface DfUpBgInkThickness2Mapper extends BaseMapper<DfUpBgInkThickness2> {

    @Select("SELECT batch_id FROM df_up_bg_ink_thickness WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();

}




