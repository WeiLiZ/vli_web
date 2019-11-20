package com.vli.controller;

import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.ArticleService;
import com.vli.vo.ArticleVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

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
    public ResultModel<ModelPageInfo<ArticleVo>>list(){
        ModelPageInfo<ArticleVo> modelPageInfo=articleService.list();
        return ResultModel.success(ResultCode.SUCCESS,modelPageInfo);
    }

    @PostMapping("/findArticleById")
    public ResultModel findArticleById(@RequestBody Map<String,Object> params){
        Integer articleId = Integer.valueOf(params.get("articleId").toString());
        ArticleVo articleVo=articleService.findArticleById(articleId);
        return ResultModel.success(ResultCode.SUCCESS,articleVo);
    }
}
