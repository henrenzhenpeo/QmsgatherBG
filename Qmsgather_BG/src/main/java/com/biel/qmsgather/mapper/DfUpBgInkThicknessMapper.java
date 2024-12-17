package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgInkThickness;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_thickness(bg油墨厚度)】的数据库操作Mapper
* @createDate 2024-12-16 15:49:19
* @Entity com.biel.qmsgather.domain.DfUpBgInkThickness
*/
@Mapper
public interface DfUpBgInkThicknessMapper extends BaseMapper<DfUpBgInkThickness> {

    @Select("SELECT batch_id FROM df_up_bg_ink_thickness WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();

}




