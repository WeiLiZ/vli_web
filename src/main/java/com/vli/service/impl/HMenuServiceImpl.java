package com.vli.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.HMenuConverter;
import com.vli.converter.ModelPageInfoConvert;
import com.vli.from.Params;
import com.vli.mapper.MenuMapper;
import com.vli.parameter.HMenuParameter;
import com.vli.po.*;
import com.vli.service.HMenuService;
import com.vli.utlis.BaseConverter;
import com.vli.vo.HMenuVo;
import com.vli.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * @author ZL
 * Created on 2020/1/6.
 */
@Service
@Transactional
public class HMenuServiceImpl implements HMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private HMenuConverter hMenuConverter;
    @Resource
    private ModelPageInfoConvert modelPageInfoConvert;

    @Override
    public ModelPageInfo<HMenuVo> list(Params params) {
        Page<Menu> page = PageHelper.startPage(params.getPage(), params.getPageSize());
        Example example = new Example(Menu.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria cc = example.createCriteria();
        cc.andEqualTo("deleteStatus",Boolean.FALSE);
        List<Menu> menus = menuMapper.selectByExample(example);
        List<HMenuVo> convert = hMenuConverter.convert(menus, HMenuVo.class);
        return modelPageInfoConvert.convertDifferentPages(page,convert);
    }

    @Override
    public ResultModel add(HttpServletRequest request, HMenuParameter params) {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus",Boolean.FALSE);
        criteria.andEqualTo("name",params.getName());
        List<Menu> menus = menuMapper.selectByExample(example);
        ResultModel<Object> resultModel = new ResultModel<>();
        if (!menus.isEmpty()){
            resultModel.setCode(ResultCode.FAILURE.code());
            resultModel.setMsg("添加的菜单名已经存在");
            resultModel.setResultCode(ResultCode.FAILURE);
            return resultModel;
        }
        BaseConverter baseConverter = new BaseConverter();
        Menu convert = (Menu) baseConverter.convert(params, Menu.class);
        convert.setUpdateTime(new Date());
        convert.setCreateTime(new Date());
        convert.setDeleteStatus(Boolean.FALSE);
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        convert.setUserId(user.getId());
        menuMapper.insert(convert);
        return ResultModel.success();
    }

    @Override
    public ResultModel update(HMenuParameter params) {
        Menu convert = (Menu) new BaseConverter().convert(params, Menu.class);
        convert.setUpdateTime(new Date());
        menuMapper.updateByPrimaryKeySelective(convert);
        return ResultModel.success();
    }

    @Override
    public ResultModel delete(HMenuParameter params) {
        Menu menu = new Menu();
        menu.setId(params.getId());
        menu.setDeleteStatus(Boolean.TRUE);
        menu.setUpdateTime(new Date());
        menuMapper.updateByPrimaryKeySelective(menu);
        return ResultModel.success();
    }
}
