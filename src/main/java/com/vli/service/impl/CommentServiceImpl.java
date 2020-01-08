package com.vli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.ModelPageInfoConvert;
import com.vli.from.CommentForm;
import com.vli.mapper.ArticleMapper;
import com.vli.mapper.CommentMapper;
import com.vli.mapper.UserMapper;
import com.vli.po.*;
import com.vli.service.CommentService;
import com.vli.utlis.BaseConverter;
import com.vli.utlis.IpUtil;
import com.vli.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author ZL
 * Created on 2019/11/20.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private ModelPageInfoConvert modelPageInfoConvert;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public void subComment(HttpServletRequest request, CommentForm form) {
        BaseConverter baseConverter = new BaseConverter();
        Comment convert = (Comment) baseConverter.convert(form, Comment.class);
        convert.setCreateTime(new Date());
        convert.setDeleteStatus(Boolean.FALSE);
        String ip = IpUtil.getUserIP(request);
        convert.setIpAddress(ip);
        //判断用户是否登录
        if (form.getUserId() != null) {
            convert.setIdentity(form.getUserId());
            //判断用户有没有绑定QQ
            User user = userMapper.selectByPrimaryKey(form.getUserId());
            if (user.getQqNumber() == null||user.getQqHealPortrait()==null) {
                //没有
                user.setQqNumber(Integer.valueOf(form.getQqNumber()));//绑定QQ
                if (user.getMailbox()==null) {
                    user.setMailbox(form.getMailbox());//绑定邮箱
                }
                user.setQqHealPortrait(form.getHeadPortrait());//添加QQ头像
                //更新用户信息
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
        //添加评论
        commentMapper.insertSelective(convert);
        //修改文章评论量
        Article article = articleMapper.selectByPrimaryKey(form.getArticleId());
        article.setCommentNum(article.getCommentNum() + 1);
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public ModelPageInfo getListComment(Integer articleId) {
        Page<Comment> page = PageHelper.startPage(1, 10);
        Example example = new Example(Comment.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId", articleId);
        criteria.andEqualTo("deleteStatus", Boolean.FALSE);
        commentMapper.selectByExample(example);
        return modelPageInfoConvert.convertPage(page);
    }

    @Override
    public Integer findCommentNum(Integer id) {
        Comment comment = new Comment();
        comment.setDeleteStatus(Boolean.FALSE);
        comment.setArticleId(id);
        List<Comment> comments = commentMapper.select(comment);
        return comments.size();
    }
}
