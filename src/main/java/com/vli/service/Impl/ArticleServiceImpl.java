package com.vli.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.ArticleConverter;
import com.vli.mapper.ArticleMapper;
import com.vli.po.Article;
import com.vli.po.ModelPageInfo;
import com.vli.service.ArticleService;
import com.vli.utlis.BaseConverter;
import com.vli.vo.ArticleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleConverter articleConverter;

    @Override
    public ModelPageInfo<ArticleVo> list() {
        Page<Article> page = PageHelper.startPage(1, 10);
        Example example = new Example(Article.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus", Boolean.FALSE);
        articleMapper.selectByExample(example);
        List<ArticleVo> convert = articleConverter.convert(page.getResult(), ArticleVo.class);
        ModelPageInfo<ArticleVo> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setTotal(page.getTotal());
        modelPageInfo.setPage(page.getPageNum());
        modelPageInfo.setPageSize(page.getPageSize());
        modelPageInfo.setData(convert);
        return modelPageInfo;
    }

    @Override
    public ArticleVo findArticleById(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        //修改浏览量
        article.setViewNum(article.getViewNum() + 1);
        articleMapper.updateByPrimaryKeySelective(article);
        return articleConverter.convert(article, ArticleVo.class);
    }

    @Override
    public ModelPageInfo getUserOtherArticle(Integer userId, Integer articleId) {
        Page<Article> page = PageHelper.startPage(1, 10);
        Example example = new Example(Article.class);
        example.setOrderByClause("create_time ASC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus", Boolean.FALSE);
        criteria.andNotEqualTo("id", articleId);
        criteria.andEqualTo("userId", userId);
        articleMapper.selectByExample(example);
        BaseConverter baseConverter = new BaseConverter();
        List<ArticleVo> convert = baseConverter.convert(page.getResult(), ArticleVo.class);
        ModelPageInfo<ArticleVo> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setData(convert);
        modelPageInfo.setPage(page.getPageNum());
        modelPageInfo.setPageSize(page.getPageSize());
        modelPageInfo.setTotal(page.getTotal());
        return modelPageInfo;
    }
}
