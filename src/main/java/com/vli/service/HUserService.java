package com.vli.service;

import com.vli.from.HUserForm;
import com.vli.parameter.HUserParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultModel;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface HUserService {
    /**
     * 查询
     * @param from
     * @return
     */
    ModelPageInfo list(HUserForm from);

    /**
     * 添加
     * @param params
     * @return
     */
    ResultModel add(HUserParameter params);

    /**
     * 修改
     * @param params
     * @return
     */
    ResultModel update(HUserParameter params);

    /**
     * 删除
     * @param params
     * @return
     */
    ResultModel delete(HUserParameter params);
}
