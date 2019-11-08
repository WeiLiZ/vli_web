package com.vli.controller;

import com.vli.po.ResultModel;
import com.vli.service.HRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vli/back/end/role")
public class HRoleController {

    @Resource
    private HRoleService hRoleService;

    @RequestMapping("/list")
    public ResultModel list(){
        ResultModel resultModel=hRoleService.list();
        return resultModel;
    }
}