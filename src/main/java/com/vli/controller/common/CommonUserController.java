package com.vli.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.vli.parameter.UserParameter;
import com.vli.po.Comment;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.utlis.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ZL
 * Created on 2020/1/8.
 */
@RestController
@RequestMapping("/vli/common/user")
public class CommonUserController {

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
                comment.setMailbox(userParameter.getQqNumber() + "@qq.com");
                return ResultModel.success(comment);
            }
            return ResultModel.failure(ResultCode.SYSTEM_INNER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.failure(ResultCode.SYSTEM_INNER_ERROR);
        }
    }
}
