package com.vli.service;

import com.vli.from.CommentForm;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultModel;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface CommentService {
    ResultModel subComment(CommentForm form);

    ModelPageInfo getListComment(Integer articleId);
}
