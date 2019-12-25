package com.vli.service;

import com.vli.parameter.UserParameter;
import com.vli.po.ResultModel;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface UserService {
    Boolean insert(UserParameter userParameter);

    ResultModel login(String userName, String password, String userIP);

    void updateLoginIp(Integer id, String ip);
}
