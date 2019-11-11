package com.vli.controller;

import com.vli.po.ModelPageInfo;
import com.vli.po.ResultModel;
import com.vli.service.HArticleService;
import com.vli.vo.HArticleVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vli/back/end/article")
public class HArticleController {

    @Resource
    private HArticleService hArticleService;

    @PostMapping("/list")
    public ResultModel<ModelPageInfo<HArticleVo>>list(){
        ModelPageInfo<HArticleVo> modelPageInfo=hArticleService.list();
        return ResultModel.success(modelPageInfo);
    }
}
