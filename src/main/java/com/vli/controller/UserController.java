package com.vli.controller;

import com.vli.po.Comment;
import com.vli.utlis.HttpClientUtil;
import com.vli.utlis.LogUtils;
import com.vli.vo.UserVo;
import com.vli.converter.UserConverter;
import com.vli.parameter.UserParameter;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.UserService;
import com.vli.utlis.IpUtil;
import com.vli.utlis.MD5;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
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
     * @param userParameter
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
     * @param request
     * @param userParameter
     * @return
     */
    @PostMapping("/login")
    public ResultModel login(HttpServletRequest request, @RequestBody UserParameter userParameter) {
        String Authorization = request.getHeader("Authorization");
        if (Authorization == null || "".equals(Authorization)) {
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

    @Value("${slt.path-one}")
    private String sltPathOne;
    /**
     * 获取QQ信息
     * @param userParameter
     * @return
     */
    @PostMapping("/getQqInformation")
    public ResultModel getQqInformation(@RequestBody UserParameter userParameter) {
        if (userParameter.getQqNumber() == null) {
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        }
        try {
            String str = HttpClientUtil.doGet(sltPathOne+"?qq=" + userParameter.getQqNumber(), "UTF-8");
            Map map = JSONObject.parseObject(str, Map.class);
            if (map.size()==3) {
                Comment comment = new Comment();
                comment.setQqNumber(userParameter.getQqNumber());
                comment.setHeadPortrait(map.get("imgurl").toString());
                comment.setNickName(map.get("name").toString());
                comment.setMail(userParameter.getQqNumber() + "@qq.com");
                return ResultModel.success(comment);
            }
            return ResultModel.failure(ResultCode.SYSTEM_INNER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }
}
