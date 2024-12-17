package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgSandBlast;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_sand_blast(bg喷砂)】的数据库操作Mapper
* @createDate 2024-12-12 15:16:27
* @Entity com.biel.qmsgather.domain.DfUpBgSandBlast
*/
@Mapper
public interface DfUpBgSandBlastMapper extends BaseMapper<DfUpBgSandBlast> {
    @Select("SELECT batch_id FROM df_up_bg_sand_blast WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




