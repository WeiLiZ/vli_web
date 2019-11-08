package com.vli.controller;

import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.ArticleService;
import com.vli.vo.ArticleVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vli/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @PostMapping("/list")
    public ResultModel<ModelPageInfo<ArticleVo>>list(){
        ModelPageInfo<ArticleVo> modelPageInfo=articleService.list();
        return ResultModel.success(ResultCode.SUCCESS,modelPageInfo);
    }
}
