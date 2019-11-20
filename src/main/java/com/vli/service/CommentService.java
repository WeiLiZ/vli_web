package com.vli.service;

import com.vli.from.CommentForm;
import com.vli.mapper.CommentMapper;
import com.vli.po.Comment;
import com.vli.po.ResultModel;
import com.vli.utlis.BaseConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author ZL
 * Created on 2019/11/20.
 */
@Service
@Transactional
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    public ResultModel subComment(CommentForm form) {
        BaseConverter baseConverter = new BaseConverter();
        Comment convert = (Comment) baseConverter.convert(form, Comment.class);
        convert.setCreateTime(new Date());
        convert.setDeleteStatus(Boolean.FALSE);
        commentMapper.insert(convert);
        return ResultModel.success();
    }
}
