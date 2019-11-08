package com.vli.controller;

import com.vli.parameter.HBackEndMenuParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.po.User;
import com.vli.service.HBackEndMenuService;
import com.vli.vo.HBackEndMenuVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/vli/back/end/menu")
public class HBackEndMenuController {

    @Resource
    private HBackEndMenuService hBackEndMenuService;

    @PostMapping("/list")
    @ResponseBody
    public ResultModel<ModelPageInfo<HBackEndMenuVo>> list(HttpServletRequest request){
        ModelPageInfo<HBackEndMenuVo> modelPageInfo= hBackEndMenuService.list();
        return ResultModel.success(ResultCode.SUCCESS,modelPageInfo);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResultModel add(HttpServletRequest request,HBackEndMenuParameter params){
        ResultModel resultModel= hBackEndMenuService.add(request,params);
        return resultModel;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResultModel update(HBackEndMenuParameter params){
        ResultModel resultModel=hBackEndMenuService.update(params);
        return resultModel;
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultModel delete(HBackEndMenuParameter params){
        ResultModel resultModel=hBackEndMenuService.delete(params);
        return resultModel;
    }
}
