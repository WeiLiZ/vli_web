package com.vli.controller.manage;

import com.vli.po.ResultModel;
import com.vli.service.HRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 权限管理类
 */
@RestController
@RequestMapping("/vli/back/end/role")
public class HRoleController {

    @Resource
    private HRoleService hRoleService;

    /**
     * 查询所有权限
     * @return
     */
    @PostMapping("/list")
    public ResultModel list(){
        ResultModel resultModel=hRoleService.list();
        return resultModel;
    }
}
