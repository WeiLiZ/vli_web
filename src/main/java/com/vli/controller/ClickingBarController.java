package com.vli.controller;

import com.vli.parameter.ClickingBarParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.service.ClickingBarService;
import com.vli.vo.ClickingBarVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 点击栏接口
 */
@RestController
@RequestMapping("/vli/clicking/bar")
public class ClickingBarController {

    @Resource
    private ClickingBarService clickingBarService;

    /**
     * 查询所有点击栏
     *
     * @return
     */
    @PostMapping("/list")
    public ResultModel<ModelPageInfo<ClickingBarVo>> list(@RequestBody ClickingBarParameter clickingBarParameter) {
        ModelPageInfo<ClickingBarVo> resultModel = clickingBarService.list(clickingBarParameter);
        return ResultModel.success(ResultCode.SUCCESS, resultModel);
    }

}
