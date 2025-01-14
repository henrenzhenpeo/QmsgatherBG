package com.biel.qmsgather.mapper;

import com.biel.qmsgather.domain.DfUpBgBaigeTest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author dafenqi
* @description 针对表【df_up_bg_baige_test(bg 百格测试/煮水百格测试)】的数据库操作Mapper
* @createDate 2024-12-18 09:05:37
* @Entity com.biel.qmsgather.domain.DfUpBgBaigeTest
*/
@Mapper
public interface DfUpBgBaigeTestMapper extends BaseMapper<DfUpBgBaigeTest> {
    @Select("SELECT batch_id FROM df_up_bg_baige_test WHERE DATE(test_date) = CURDATE() ORDER BY batch_id DESC LIMIT 1")
    public String getMaxBatchId();
}




