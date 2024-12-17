package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgGrindingBottom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_grinding_bottom(bg 磨底)】的数据库操作Mapper
* @createDate 2024-12-12 15:15:34
* @Entity com.biel.qmsgather.domain.DfUpBgGrindingBottom
*/
@Mapper
public interface DfUpBgGrindingBottomMapper extends BaseMapper<DfUpBgGrindingBottom> {
    @Select("SELECT batch_id FROM df_up_bg_grinding_bottom WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




