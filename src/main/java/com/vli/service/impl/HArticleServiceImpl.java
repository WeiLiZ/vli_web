package com.vli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.HArticleConverter;
import com.vli.converter.ModelPageInfoConvert;
import com.vli.from.Params;
import com.vli.mapper.ArticleMapper;
import com.vli.parameter.HArticleParameter;
import com.vli.po.Article;
import com.vli.po.ModelPageInfo;
import com.vli.service.HArticleService;
import com.vli.utlis.BaseConverter;
import com.vli.vo.HArticleVo;
import com.vli.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class HArticleServiceImpl implements HArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ModelPageInfoConvert modelPageInfoConvert;

    @Resource
    private HArticleConverter hArticleConverter;

    @Override
    public ModelPageInfo<HArticleVo> list(Params params) {
        Page<Article> page = PageHelper.startPage(params.getPage(), params.getPageSize());
        Example example = new Example(Article.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus", Boolean.FALSE);
        articleMapper.selectByExample(example);
        List<HArticleVo> convert = hArticleConverter.convert(page.getResult(), HArticleVo.class);
        return modelPageInfoConvert.convertDifferentPages(page,convert);
    }

    @Override
    public void add(HttpServletRequest request, HArticleParameter hArticleVo) {
        BaseConverter baseConverter = new BaseConverter();
        Article convert = (Article) baseConverter.convert(hArticleVo, Article.class);
        convert.setUpdateTime(new Date());
        convert.setCreateTime(new Date());
        convert.setDeleteStatus(Boolean.FALSE);
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        convert.setUserId(user.getId());
        articleMapper.insert(convert);
    }

    @Override
    public void update(HArticleParameter hArticleVo) {
        BaseConverter baseConverter = new BaseConverter();
        Article convert = (Article) baseConverter.convert(hArticleVo, Article.class);
        convert.setUpdateTime(new Date());
        articleMapper.updateByPrimaryKeySelective(convert);
    }

    @Override
    public void delete(HArticleParameter hArticleVo) {
        Article article = new Article();
        article.setId(hArticleVo.getId());
        article.setUpdateTime(new Date());
        article.setDeleteStatus(Boolean.TRUE);
        articleMapper.updateByPrimaryKeySelective(article);
    }
}
