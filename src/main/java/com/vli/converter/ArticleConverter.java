package com.vli.converter;

import com.vli.mapper.UserMapper;
import com.vli.po.Article;
import com.vli.service.CommentService;
import com.vli.utlis.BaseConverter;
import com.vli.vo.ArticleVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ArticleConverter extends BaseConverter<Article, ArticleVo> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CommentService commentService;

    @Override
    public void convert(Article from, ArticleVo to) {
        if (from.getUserId() != null) {
            to.setUserName(userMapper.selectByPrimaryKey(from.getUserId()).getUserName());
        }
        Integer commentNum = commentService.findCommentNum(from.getId());
        to.setCommentNum(commentNum == 0 || commentNum == null ? 0 : commentNum);
        super.convert(from, to);
    }
}
