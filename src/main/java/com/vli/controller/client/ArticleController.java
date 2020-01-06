package com.vli.controller.client;

import com.vli.from.Params;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.ArticleService;
import com.vli.vo.ArticleVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 文章接口
 */
@RestController
@RequestMapping("/vli/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * 查询所有文章
     * @return
     */
    @PostMapping("/list")
    public ResultModel<ModelPageInfo<ArticleVo>>list(@RequestBody Params params){
        ModelPageInfo<ArticleVo> modelPageInfo=articleService.list(params);
        return ResultModel.success(ResultCode.SUCCESS,modelPageInfo);
    }

    /**
     * 获取文章详情 根据id查询文章
     * @param params 查询条件
     * @return
     */
    @PostMapping("/findArticleById")
    public ResultModel findArticleById(@RequestBody Params params){
        Integer articleId = Integer.valueOf(params.getMap().get("articleId").toString());
        if (null==articleId){
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        ArticleVo articleVo=articleService.findArticleById(articleId);
        return ResultModel.success(ResultCode.SUCCESS,articleVo);
    }

    /**
     * 获取用户其他文章  根据用户id查询
     * @param params 查询条件
     * @return
     */
    @PostMapping("/getUserOtherArticle")
    public ResultModel getUserOtherArticle(@RequestBody Params params){
        Integer userId = Integer.valueOf(params.getMap().get("userId").toString());
        if (userId==null){
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        Integer articleId = Integer.valueOf(params.getMap().get("articleId").toString());
        if (articleId==null){
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        ModelPageInfo modelPageInfo=articleService.getUserOtherArticle(userId,articleId);
        return ResultModel.success(modelPageInfo);
    }

}
