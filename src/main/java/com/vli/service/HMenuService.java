package com.vli.service;

import com.vli.from.Params;
import com.vli.parameter.HMenuParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultModel;
import com.vli.vo.HMenuVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZL
 * Created on 2020/1/6.
 */
public interface HMenuService {

    /**
     * 查询所有前端的菜单导航栏
     * @param params 查询参数
     * @return
     */
    ModelPageInfo<HMenuVo> list(Params params);

    /**
     * 添加菜单
     * @param request
     * @param params
     * @return
     */
    ResultModel add(HttpServletRequest request, HMenuParameter params);

    /**
     * 修改菜单
     * @param params
     * @return
     */
    ResultModel update(HMenuParameter params);

    /**
     * 删除菜单
     * @param params
     * @return
     */
    ResultModel delete(HMenuParameter params);
}
