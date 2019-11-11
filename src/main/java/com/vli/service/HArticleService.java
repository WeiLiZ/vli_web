package com.vli.service;

import com.vli.mapper.ArticleMapper;
import com.vli.po.ModelPageInfo;
import com.vli.vo.HArticleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class HArticleService {

    @Resource
    private ArticleMapper articleMapper;

    public ModelPageInfo<HArticleVo> list() {
        return null;
    }
}
