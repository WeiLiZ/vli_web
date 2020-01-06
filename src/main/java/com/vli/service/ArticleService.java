package com.vli.service;

import com.vli.from.Params;
import com.vli.po.ModelPageInfo;
import com.vli.vo.ArticleVo;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface ArticleService {

    /**
     * 查询所有文章
     * @Params 条件
     * @return
     */
    ModelPageInfo<ArticleVo> list(Params params);

    /**
     * 获取文章详情 根据id查询文章
     * @id 文章id
     * @return
     */
    ArticleVo findArticleById(Integer id);

    ModelPageInfo getUserOtherArticle(Integer userId, Integer articleId);
}
