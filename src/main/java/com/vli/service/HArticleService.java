package com.vli.service;

import com.vli.from.Params;
import com.vli.parameter.HArticleParameter;
import com.vli.po.ModelPageInfo;
import com.vli.vo.HArticleVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface HArticleService {
    ModelPageInfo<HArticleVo> list(Params params);

    void add(HttpServletRequest request, HArticleParameter hArticleVo);

    void update(HArticleParameter hArticleVo);

    void delete(HArticleParameter hArticleVo);
}
