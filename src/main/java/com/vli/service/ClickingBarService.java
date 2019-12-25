package com.vli.service;

import com.vli.parameter.ClickingBarParameter;
import com.vli.po.ModelPageInfo;
import com.vli.vo.ClickingBarVo;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface ClickingBarService {
    ModelPageInfo<ClickingBarVo> list(ClickingBarParameter clickingBarParameter);
}
