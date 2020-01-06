package com.vli.service;

import com.vli.parameter.UserParameter;
import com.vli.po.ResultModel;

/**
 * @author ZL
 * Created on 2019/12/25.
 */
public interface UserService {
    Boolean insert(UserParameter userParameter);

    /**
     * 登陆
     * @param userName 用户名
     * @param password 密码
     * @param userIP 登陆ip
     * @return
     */
    ResultModel login(String userName, String password, String userIP);

    /**
     * 修改登陆ip
     * @param id 用户id
     * @param ip 登陆ip
     */
    void updateLoginIp(Integer id, String ip);
}
