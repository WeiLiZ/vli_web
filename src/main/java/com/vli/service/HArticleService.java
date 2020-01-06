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
    /**
     * 查询所有文章
     * @param params 条件类
     * @return
     */
    ModelPageInfo<HArticleVo> list(Params params);

    /**
     * 添加文章
     * @param request 请求
     * @param hArticleVo 添加数据
     */
    void add(HttpServletRequest request, HArticleParameter hArticleVo);

    /**
     * 修改文章
     * @param hArticleVo 修改数据
     */
    void update(HArticleParameter hArticleVo);

    /**
     * 删除文章
     * @param hArticleVo 删除数据
     */
    void delete(HArticleParameter hArticleVo);
}
