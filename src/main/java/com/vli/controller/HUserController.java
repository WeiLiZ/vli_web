package com.vli.controller;

import com.vli.from.HUserFrom;
import com.vli.parameter.HUserParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.HUserService;
import com.vli.vo.HUserVo;
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
     * @return
     */
    @PostMapping("/list")
    public ResultModel<ModelPageInfo<HUserVo>> list(HUserFrom from) {
        ModelPageInfo modelPageInfo = hUserService.list(from);
        return ResultModel.success(ResultCode.SUCCESS, modelPageInfo);
    }

    /**
     * 添加
     * @param request
     * @param params
     * @return
     */
    @PostMapping("/add")
    public ResultModel add(HttpServletRequest request, HUserParameter params) {
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        if (user.getRoleId() == 3) {
            return ResultModel.failure(ResultCode.PERMISSION_NO_ACCESS);
        }
        ResultModel resultModel = hUserService.add(params);
        return resultModel;
    }

    /**
     * 修改
     * @param request
     * @param params
     * @return
     */
    @PostMapping("/update")
    public ResultModel update(HttpServletRequest request, HUserParameter params) {
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        if (user.getRoleId() != 1) {
            return ResultModel.failure(ResultCode.PERMISSION_NO_ACCESS);
        }
        ResultModel resultModel = hUserService.update(params);
        return resultModel;
    }

    /**
     * 删除
     * @param request
     * @param params
     * @return
     */
    @PostMapping("/delete")
    public ResultModel delete(HttpServletRequest request, HUserParameter params) {
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        if (user.getRoleId() != 1) {
            return ResultModel.failure(ResultCode.PERMISSION_NO_ACCESS);
        }
        ResultModel resultModel = hUserService.delete(params);
        return resultModel;
    }
}
