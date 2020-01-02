package com.vli.service;

import com.vli.po.Menu;

import java.util.List;

/**
 * @author ZL
 * Created on 2019/12/31.
 */
public interface MenuService {

    /**
     * 查询所有的导航栏菜单
     * @return
     */
    List<Menu> list();
}
