package com.vli.converter;

import com.vli.mapper.UserMapper;
import com.vli.po.BackEndMenu;
import com.vli.utlis.BaseConverter;
import com.vli.vo.HBackEndMenuVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HBackEndMenuConverter extends BaseConverter<BackEndMenu, HBackEndMenuVo> {

    @Resource
    private UserMapper userMapper;

    @Override
    public void convert(BackEndMenu from, HBackEndMenuVo to) {
        if (from.getUserId()!=null){
            to.setUserName(userMapper.selectByPrimaryKey(from.getUserId()).getUserName());
        }
        super.convert(from, to);
    }
}
