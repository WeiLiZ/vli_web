package com.vli.controller.manage;

import com.vli.from.HUserForm;
import com.vli.parameter.HUserParameter;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.HUserService;
import com.vli.vo.UserVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/vli/back/end/user")
public class HUserController {

    @Resource
    private HUserService hUserService;

    /**
     * 查询
     *
     * @return
     */
    @PostMapping("/list")
    public ResultModel list(HUserForm from) {
        return ResultModel.success(ResultCode.SUCCESS, hUserService.list(from));
    }

    /**
     * 添加
     *
     * @param request
     * @param params
     * @return
     */
    @PostMapping("/add")
    public ResultModel add(HttpServletRequest request, HUserParameter params) {
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        if (user.getRoleId() == 3)
            return ResultModel.failure(ResultCode.PERMISSION_NO_ACCESS);
        if ("".equals(params.getUserName()))
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        if ("".equals(params.getPassword()))
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        if ("".equals(params.getPhone()))
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        if (params.getQqNumber()==null)
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        if ("".equals(params.getMailbox()))
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK);
        return hUserService.add(params);
    }

    /**
     * 修改
     *
     * @param request
     * @param params
     * @return
     */
    @PostMapping("/update")
    public ResultModel update(HttpServletRequest request, HUserParameter params) {
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        if (user.getRoleId() != 1)
            return ResultModel.failure(ResultCode.PERMISSION_NO_ACCESS);
        if ("".equals(params.getUserName()))
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK, "用户名不能为空！");
        if ("".equals(params.getPhone()))
            return ResultModel.failure(ResultCode.PARAM_IS_BLANK, "手机号不能为空！");
        return hUserService.update(params);
    }

    /**
     * 删除
     *
     * @param request
     * @param params
     * @return
     */
    @PostMapping("/delete")
    public ResultModel delete(HttpServletRequest request, HUserParameter params) {
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        if (user.getRoleId() != 1)
            return ResultModel.failure(ResultCode.PERMISSION_NO_ACCESS);
        return hUserService.delete(params);
    }
}
