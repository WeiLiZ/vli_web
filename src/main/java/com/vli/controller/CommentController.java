package com.vli.controller;

import com.vli.from.CommentForm;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * 评论接口
 * @author ZL
 * Created on 2019/11/20.
 */
@RestController
@RequestMapping("/vli/comment")
public class CommentController {

    @Resource
    private CommentService commentService;



    /**
     * 添加评论
     * @param form 添加的数据
     * @return
     */
    @PostMapping("/subComment")
    public ResultModel subComment(@RequestBody CommentForm form){
        if (form.getMail().equals("")){
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        if (form.getNickName().equals("")){
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        if (form.getArticleId()==null){
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        if (form.getQqNumber().equals("")){
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        if (form.getContent().equals("")){
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        ResultModel resultModel=commentService.subComment(form);
        return ResultModel.success(resultModel);
    }

}
