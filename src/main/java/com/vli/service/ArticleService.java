package com.vli.service;

import com.vli.po.ModelPageInfo;
import com.vli.vo.ArticleVo;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface ArticleService {
    ModelPageInfo<ArticleVo> list();

    ArticleVo findArticleById(Integer id);

    ModelPageInfo getUserOtherArticle(Integer userId, Integer articleId);
}
