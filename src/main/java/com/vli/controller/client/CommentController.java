package com.vli.controller.client;

import com.vli.from.CommentForm;
import com.vli.from.Params;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.CommentService;
import com.vli.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 评论接口
 *
 * @author ZL
 * Created on 2019/11/20.
 */
@RestController
@RequestMapping("/vli/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 查询评论根据文章id
     * @param params 存放的参数【articleId】
     * @return
     */
    @PostMapping("/getListComment")
    public ResultModel getListComment(@RequestBody Params params) {
        Integer articleId = Integer.valueOf(params.getMap().get("articleId").toString());
        if (articleId==null){
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        return ResultModel.success(commentService.getListComment(articleId));
    }

    /**
     * 添加评论
     *
     * @param form 添加的数据
     * @return
     */
    @PostMapping("/subComment")
    public ResultModel subComment(HttpServletRequest request, @RequestBody CommentForm form) {
        if ("".equals(form.getMailbox())||null==form.getMailbox()) {
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        if ("".equals(form.getNickName())||null==form.getNickName()) {
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        if (null==form.getArticleId() ) {
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        if ("".equals(form.getQqNumber())||null==form.getQqNumber()) {
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        if ("".equals(form.getContent())||null==form.getContent()) {
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        commentService.subComment(request,form);
        return ResultModel.success();
    }

}
