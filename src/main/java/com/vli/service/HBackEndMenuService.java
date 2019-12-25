package com.vli.service;

import com.vli.parameter.HBackEndMenuParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultModel;
import com.vli.vo.HBackEndMenuVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface HBackEndMenuService {
    ModelPageInfo<HBackEndMenuVo> list();

    ResultModel add(HttpServletRequest request, HBackEndMenuParameter params);

    ResultModel update(HBackEndMenuParameter params);

    ResultModel delete(HBackEndMenuParameter params);
}
