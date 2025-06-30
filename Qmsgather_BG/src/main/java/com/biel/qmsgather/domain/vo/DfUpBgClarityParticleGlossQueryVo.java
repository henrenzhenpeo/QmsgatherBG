package com.biel.qmsgather.domain.vo;

import com.biel.qmsgather.domain.DfOrtClArityDetail;
import com.biel.qmsgather.domain.DfOrtClArityResult;
import com.biel.qmsgather.domain.DfOrtGlossDetail;
import com.biel.qmsgather.domain.DfOrtGlossResult;
import com.biel.qmsgather.domain.DfOrtGrAininessDetail;
import com.biel.qmsgather.domain.DfOrtGrAininessResult;
import lombok.Data;

import java.util.List;

@Data
public class DfUpBgClarityParticleGlossQueryVo {
    private DfOrtClArityResult clarityResult;
    private List<DfOrtClArityDetail> clarityDetailList;

    private DfOrtGlossResult glossResult;
    private List<DfOrtGlossDetail> glossDetailList;

    private DfOrtGrAininessResult graininessResult;
    private List<DfOrtGrAininessDetail> graininessDetailList;
}
