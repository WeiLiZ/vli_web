package com.vli.controller.client;

import com.vli.from.CommentForm;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
     * @param map 存放的参数【articleId】
     * @return
     */
    @PostMapping("/getListComment")
    public ResultModel getListComment(@RequestBody Map<String, Object> map) {
        Integer articleId = Integer.valueOf(map.get("articleId").toString());
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
    public ResultModel subComment(@RequestBody CommentForm form) {
        if ("".equals(form.getMail())||null==form.getMail()) {
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
        ResultModel resultModel = commentService.subComment(form);
        return ResultModel.success(resultModel);
    }

}
