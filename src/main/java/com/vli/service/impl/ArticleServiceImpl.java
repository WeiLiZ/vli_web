package com.vli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.ArticleConverter;
import com.vli.converter.ModelPageInfoConvert;
import com.vli.from.Params;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleConverter articleConverter;

    @Resource
    private ModelPageInfoConvert modelPageInfoConvert;

    @Override
    public ModelPageInfo<ArticleVo> list(Params params) {
        Page<Article> page = PageHelper.startPage(params.getPage(), params.getPageSize());
        Integer mode = Integer.valueOf(params.getMap().get("mode").toString());
        if (mode==0){
            Example example = new Example(Article.class);
            example.setOrderByClause("create_time DESC");
            example.createCriteria().andEqualTo("deleteStatus", Boolean.FALSE);
            articleMapper.selectByExample(example);
        }else{
            Article article = new Article();
            article.setDeleteStatus(Boolean.FALSE);
            articleMapper.select(article);
        }
        List<ArticleVo> convert;
        convert = articleConverter.convert(page.getResult(), ArticleVo.class);
        //1为热门推荐  2为点击排行
        if (mode == 1) {
            //通过评论量排序
            convert = convert.stream().sorted(Comparator.comparing(ArticleVo::getCommentNum).reversed()).collect(Collectors.toList());
        } else if (mode == 2) {
            //通过浏览量排序
            convert = convert.stream().sorted(Comparator.comparing(ArticleVo::getViewNum).reversed()).collect(Collectors.toList());
        }
        return modelPageInfoConvert.convertDifferentPages(page, convert);
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
        return modelPageInfoConvert.convertDifferentPages(page, convert);
    }
}
