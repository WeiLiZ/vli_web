package com.vli.service;

import com.vli.from.CommentForm;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultModel;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface CommentService {

    /**
     * 添加评论
     *
     * @param form 添加的数据
     * @return
     */
    ResultModel subComment(CommentForm form);

    ModelPageInfo getListComment(Integer articleId);

    /**
     * 通过文章id查询评论数量
     * @param id 文章id
     * @return
     */
    Integer findCommentNum(Integer id);
}
