package com.vli.controller.client;

import com.vli.po.Comment;
import com.vli.service.UserService;
import com.vli.utlis.HttpClientUtil;
import com.vli.vo.UserVo;
import com.vli.converter.UserConverter;
import com.vli.parameter.UserParameter;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.utlis.IpUtil;
import com.vli.utlis.MD5;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/vli/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserConverter userConverter;

    /**
     * 注册
     *
     * @param userParameter 注册信息
     * @return
     */
    @PostMapping("/register")
    public ResultModel register(@RequestBody UserParameter userParameter) {
        if (!userService.insert(userParameter)) {
            return ResultModel.failure(ResultCode.USER_HAS_EXISTED);
        }
        return ResultModel.success(ResultCode.SUCCESS);
    }

    /**
     * 登陆
     *
     * @param request 请求
     * @param userParameter 登陆信息
     * @return
     */
    @PostMapping("/login")
    public ResultModel login(HttpServletRequest request, @RequestBody UserParameter userParameter) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || "".equals(authorization)) {
            String userIP = IpUtil.getUserIP(request);
            try {
                ResultModel resultModel = userService.login(userParameter.getUserName(), userParameter.getPassword(), userIP);
                HashMap<String, Object> map = new HashMap<>();
                UserVo data = (UserVo) resultModel.getData();
                map.put("token", MD5.MD5(data.getUserName()) + MD5.MD5(data.getPhone()));
                map.put("user", data);
                return ResultModel.success(ResultCode.SUCCESS, map);
            } catch (Exception e) {
                ResultCode userLoginError = ResultCode.USER_LOGIN_ERROR;
                return ResultModel.failure(userLoginError);
            }
        }
        return ResultModel.failure(ResultCode.USER_HAS_LOGIN);
    }

}
