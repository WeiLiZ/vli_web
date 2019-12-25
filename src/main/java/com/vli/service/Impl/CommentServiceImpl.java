package com.vli.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.ModelPageInfoConvert;
import com.vli.from.CommentForm;
import com.vli.mapper.CommentMapper;
import com.vli.po.Comment;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultModel;
import com.vli.service.CommentService;
import com.vli.utlis.BaseConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

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

    @Override
    public ResultModel subComment(CommentForm form) {
        BaseConverter baseConverter = new BaseConverter();
        Comment convert = (Comment) baseConverter.convert(form, Comment.class);
        convert.setCreateTime(new Date());
        convert.setDeleteStatus(Boolean.FALSE);
        commentMapper.insert(convert);
        return ResultModel.success();
    }

    @Override
    public ModelPageInfo getListComment(Integer articleId) {
        Page<Comment> page = PageHelper.startPage(1, 10);
        Example example = new Example(Comment.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("articleId",articleId);
        criteria.andEqualTo("deleteStatus",Boolean.FALSE);
        commentMapper.selectByExample(example);
        ModelPageInfo<Comment> modelPageInfo = modelPageInfoConvert.convertPage(page);
        return modelPageInfo;
    }
}
