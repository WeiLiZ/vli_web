package com.vli.converter;

import com.vli.mapper.TypeMapper;
import com.vli.mapper.UserMapper;
import com.vli.po.Article;
import com.vli.utlis.BaseConverter;
import com.vli.vo.HArticleVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HArticleConverter extends BaseConverter<Article, HArticleVo> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TypeMapper typeMapper;

    @Override
    public void convert(Article from, HArticleVo to) {
        //设置用户名
        if (from.getUserId()!=null){
            to.setUserName(userMapper.selectByPrimaryKey(from.getUserId()).getUserName());
        }
        //设置类型名
        if (from.getTypeId()!=null){
            to.setTypeName(typeMapper.selectByPrimaryKey(from.getTypeId()).getTypeName());
        }
        super.convert(from, to);
    }
}
