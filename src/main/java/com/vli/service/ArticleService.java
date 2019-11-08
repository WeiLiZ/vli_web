package com.vli.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.ArticleConverter;
import com.vli.converter.ModelPageInfoConvert;
import com.vli.mapper.ArticleMapper;
import com.vli.po.Article;
import com.vli.po.ModelPageInfo;
import com.vli.vo.ArticleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleConverter articleConverter;

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
}