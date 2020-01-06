package com.vli.controller.manage;

import com.vli.parameter.UserParameter;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.UserService;
import com.vli.utlis.IpUtil;
import com.vli.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * 登陆管理类
 */
@Controller
@RequestMapping("/vli/back/end")
public class HLoginController {

    @Resource
    private UserService userService;

    /**
     * 登陆
     * @param request
     * @param userParameter
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultModel login(HttpServletRequest request, UserParameter userParameter)  {
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        if (user == null || user.equals("")) {
            String userIP = IpUtil.getUserIP(request);
                ResultModel resultModel = userService.login(userParameter.getUserName(), userParameter.getPassword(), userIP);
                if (resultModel.getData()!=null){
                    UserVo data = (UserVo) resultModel.getData();
                    request.getSession().setAttribute("user", data);
                    return ResultModel.success(ResultCode.SUCCESS);
                }else {
                    return resultModel;
                }
        } else {
            return ResultModel.failure(ResultCode.USER_HAS_LOGIN);
        }
    }

    /**
     * 退出登录
     * @param request
     * @return
     * @throws ServletException
     */
    @PostMapping("/logout")
    @ResponseBody
    public ResultModel logout(HttpServletRequest request) throws ServletException {
        request.logout();
        request.getSession().removeAttribute("user");
        return ResultModel.success();
    }
}
