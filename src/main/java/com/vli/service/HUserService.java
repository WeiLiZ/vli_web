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
    ModelPageInfo list(HUserForm from);

    ResultModel add(HUserParameter params);

    ResultModel update(HUserParameter params);

    ResultModel delete(HUserParameter params);
}
