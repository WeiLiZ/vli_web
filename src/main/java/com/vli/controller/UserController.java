package com.vli.controller;

import com.vli.vo.UserVo;
import com.vli.converter.UserConverter;
import com.vli.parameter.UserParameter;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.po.User;
import com.vli.service.UserService;
import com.vli.utlis.IpUtil;
import com.vli.utlis.MD5;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserConverter userConverter;

    @PostMapping("/register")
    public ResultModel register(@RequestBody UserParameter userParameter) {
        if (!userService.insert(userParameter)) {
            return ResultModel.failure(ResultCode.USER_HAS_EXISTED);
        }
        return ResultModel.success(ResultCode.SUCCESS);
    }

    @PostMapping("/login")
    public ResultModel login(HttpServletRequest request, HttpSession session, @RequestBody UserParameter userParameter) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || "".equals(authorization)) {
            String userIP = IpUtil.getUserIP(request);
            try {
                ResultModel resultModel = userService.login(userParameter.getUserName(), userParameter.getPassword(), userIP);
                HashMap<String, Object> map = new HashMap<>();
                User data = (User) resultModel.getData();
                UserVo convert = userConverter.convert(data, UserVo.class);
                map.put("token", MD5.MD5(data.getUserName()+data.getPassword()));
                map.put("user",convert);
                return ResultModel.success(ResultCode.SUCCESS,map);
            } catch (Exception e) {
                ResultCode userLoginError = ResultCode.USER_LOGIN_ERROR;
                return ResultModel.failure(userLoginError);
            }
        }
        return ResultModel.failure(ResultCode.USER_HAS_LOGIN);
    }

}
