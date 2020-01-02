package com.vli.service.impl;

import com.vli.converter.ModelPageInfoConvert;
import com.vli.mapper.MenuMapper;
import com.vli.po.Menu;
import com.vli.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZL
 * Created on 2019/12/31.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private ModelPageInfoConvert modelPageInfoConvert;

    @Override
    public List<Menu> list() {
        Menu menu = new Menu();
        menu.setDeleteStatus(Boolean.FALSE);
        return menuMapper.select(menu);
    }
}
