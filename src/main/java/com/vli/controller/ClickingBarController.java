package com.vli.controller;

import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.ClickingBarService;
import com.vli.vo.ClickingBarVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vli/clicking/bar")
public class ClickingBarController {

    @Resource
    private ClickingBarService clickingBarService;

    @PostMapping("/list")
    public ResultModel<ModelPageInfo<ClickingBarVo>> list(){
        ModelPageInfo<ClickingBarVo> resultModel=clickingBarService.list();
        return ResultModel.success(ResultCode.SUCCESS,resultModel);
    }

}
