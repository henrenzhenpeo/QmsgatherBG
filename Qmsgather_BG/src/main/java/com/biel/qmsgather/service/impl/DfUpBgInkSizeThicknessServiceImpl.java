package com.biel.qmsgather.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biel.qmsgather.domain.DfUpBgInkSizeThickness;
import com.biel.qmsgather.service.DfUpBgInkSizeThicknessService;
import com.biel.qmsgather.mapper.DfUpBgInkSizeThicknessMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author dafenqi
* @description 针对表【df_up_bg_ink_size_thickness(油墨尺寸(厚度))】的数据库操作Service实现
* @createDate 2024-12-16 15:49:14
*/
@Service
@Transactional
public class DfUpBgInkSizeThicknessServiceImpl extends ServiceImpl<DfUpBgInkSizeThicknessMapper, DfUpBgInkSizeThickness>
    implements DfUpBgInkSizeThicknessService{

}




