package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgInkLandHeight;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_land_height(油墨厚度+机油高度)】的数据库操作Mapper
* @createDate 2024-12-10 17:31:21
* @Entity generator.domain.DfUpBgInkLandHeight
*/
@Mapper
public interface DfUpBgInkLandHeightMapper extends BaseMapper<DfUpBgInkLandHeight> {
    @Select("SELECT batch_id FROM df_up_bg_ink_land_height WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




