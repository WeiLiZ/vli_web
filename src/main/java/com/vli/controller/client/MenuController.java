package com.vli.controller.client;

import com.vli.po.Menu;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.MenuService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZL
 * Created on 2019/12/31.
 */
@RestController
@RequestMapping("/vli/menu")
public class MenuController {


    @Resource
    private MenuService menuService;

    /**
     * 查询所有的导航栏菜单
     *
     * @return
     */
    @PostMapping("/list")
    public ResultModel list() {
        ResultModel<List<Menu>> resultModel = new ResultModel<>();
        resultModel.setData(menuService.list());
        resultModel.setResultCode(ResultCode.SUCCESS);
        return resultModel;
    }
}
