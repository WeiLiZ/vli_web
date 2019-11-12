package com.vli.controller;

import com.vli.parameter.HArticleParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultModel;
import com.vli.service.HArticleService;
import com.vli.vo.HArticleVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/vli/back/end/article")
public class HArticleController {

    @Resource
    private HArticleService hArticleService;

    /**
     *
     * @return
     */
    @PostMapping("/list")
    public ResultModel<ModelPageInfo<HArticleVo>>list(){
        ModelPageInfo<HArticleVo> modelPageInfo=hArticleService.list();
        return ResultModel.success(modelPageInfo);
    }

    /**
     *
     * @param params
     * @return
     */
    @PostMapping("/add")
    public ResultModel add(HttpServletRequest request,HArticleParameter params){
        hArticleService.add(request,params);
        return ResultModel.success();
    }

    /**
     *
     * @param params
     * @return
     */
    @PostMapping("/update")
    public ResultModel update(HArticleParameter params){
        hArticleService.update(params);
        return ResultModel.success();
    }

    /**
     *
     * @param params
     * @return
     */
    @PostMapping("/delete")
    public ResultModel delete(HArticleParameter params){
        hArticleService.delete(params);
        return ResultModel.success();
    }
}
