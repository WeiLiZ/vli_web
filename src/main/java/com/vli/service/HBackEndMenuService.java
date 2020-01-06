package com.vli.service;

import com.vli.parameter.HBackEndMenuParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultModel;
import com.vli.vo.HBackEndMenuVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface HBackEndMenuService {

    /**
     * 查询所有后端菜单
     * @return
     */
    ModelPageInfo<HBackEndMenuVo> list();

    /**
     * 添加后端菜单
     * @param request 请求
     * @param params 添加数据
     * @return
     */
    ResultModel add(HttpServletRequest request, HBackEndMenuParameter params);

    /**
     * 修改后端菜单
     * @param params 修改数据
     * @return
     */
    ResultModel update(HBackEndMenuParameter params);

    /**
     * 删除后端菜单
     * @param params 删除数据
     * @return
     */
    ResultModel delete(HBackEndMenuParameter params);
}
