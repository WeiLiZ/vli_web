package com.vli.controller.manage;

import com.vli.from.Params;
import com.vli.parameter.HMenuParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.HMenuService;
import com.vli.vo.HMenuVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * 菜单导航栏管理类
 * @author ZL
 * Created on 2019/11/22.
 */
@RestController
@RequestMapping("/vli/back/end/front/menu")
public class HMenuController {

    @Resource
    private HMenuService hMenuService;

    /**
     * 查询菜单
     * @param params 参数类
     * @return
     */
    @PostMapping("/list")
    public ResultModel<ModelPageInfo<HMenuVo>> list(Params params) {
        return ResultModel.success(ResultCode.SUCCESS,hMenuService.list(params));
    }

    /**
     * 添加菜单
     * @param request
     * @param params
     * @return
     */
    @PostMapping("/add")
    public ResultModel add(HttpServletRequest request, HMenuParameter params){
        return hMenuService.add(request,params);
    }

    /**
     * 修改菜单
     * @param params
     * @return
     */
    @PostMapping("/update")
    public ResultModel update(HMenuParameter params){
        return hMenuService.update(params);
    }

    @PostMapping("/delete")
    public ResultModel delete(HMenuParameter params){
        return hMenuService.delete(params);
    }

}
