package com.vli.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.HArticleConverter;
import com.vli.converter.ModelPageInfoConvert;
import com.vli.mapper.ArticleMapper;
import com.vli.po.Article;
import com.vli.po.ModelPageInfo;
import com.vli.vo.HArticleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class HArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ModelPageInfoConvert modelPageInfoConvert;

    @Resource
    private HArticleConverter hArticleConverter;

    public ModelPageInfo<HArticleVo> list() {
        Page<Article> page = PageHelper.startPage(1, 10);
        Example example = new Example(Article.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatic", Boolean.FALSE);
        articleMapper.selectByExample(example);
        List<HArticleVo> convert = hArticleConverter.convert(page.getResult(), HArticleVo.class);
        ModelPageInfo<HArticleVo> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setTotal(page.getTotal());
        modelPageInfo.setPageSize(page.getPageSize());
        modelPageInfo.setPage(page.getPageNum());
        modelPageInfo.setData(convert);
        return modelPageInfo;
    }
}
