package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgInkDensity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_density(油墨密度- OD)】的数据库操作Mapper
* @createDate 2024-12-10 17:31:13
* @Entity generator.domain.DfUpBgInkDensity
*/
@Mapper
public interface DfUpBgInkDensityMapper extends BaseMapper<DfUpBgInkDensity> {

    @Select("SELECT batch_id FROM df_up_bg_ink_density WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




