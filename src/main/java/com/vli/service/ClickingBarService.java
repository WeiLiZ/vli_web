package com.vli.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.ClickingBarConverter;
import com.vli.parameter.ClickingBarParameter;
import com.vli.mapper.ArticleMapper;
import com.vli.mapper.ClickingBarMapper;
import com.vli.po.Article;
import com.vli.po.ClickingBar;
import com.vli.po.ModelPageInfo;
import com.vli.vo.ClickingBarVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClickingBarService {

    @Resource
    private ClickingBarMapper clickingBarMapper;

    @Resource
    private ClickingBarConverter clickingBarConverter;

    @Resource
    private ArticleMapper articleMapper;

    public ModelPageInfo<ClickingBarVo> list(ClickingBarParameter clickingBarParameter) {
        Example example = new Example(ClickingBar.class);
        example.setOrderByClause("create_time ASC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus", Boolean.FALSE);
        Page<ClickingBar> page = PageHelper.startPage(clickingBarParameter.getPage(), clickingBarParameter.getPageSize());
        clickingBarMapper.selectByExample(example);
        List<ClickingBarVo> convert = clickingBarConverter.convert(page.getResult(), ClickingBarVo.class);
        convert.forEach(query->{
            Article article = new Article();
            article.setDeleteStatus(Boolean.FALSE);
            article.setClickingBarId(query.getId());
            PageHelper.startPage(clickingBarParameter.getPage(), clickingBarParameter.getPageSize());
            List<Article> articleList = articleMapper.select(article);
            ArrayList<String> arrayList = new ArrayList<>();
            articleList.forEach(arr->{
                arrayList.add(arr.getHomePic());
            });
            if (!arrayList.isEmpty()){
                query.setImgs(arrayList);
            }
        });
        ModelPageInfo<ClickingBarVo> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setData(convert);
        modelPageInfo.setPage(page.getPageNum());
        modelPageInfo.setPageSize(page.getPageSize());
        modelPageInfo.setTotal(page.getTotal());
        return modelPageInfo;
    }
}
