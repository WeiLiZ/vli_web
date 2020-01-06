package com.vli.converter;

import com.vli.mapper.UserMapper;
import com.vli.po.Menu;
import com.vli.utlis.BaseConverter;
import com.vli.vo.HMenuVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ZL
 * Created on 2020/1/6.
 */
@Component
public class HMenuConverter extends BaseConverter<Menu, HMenuVo> {

    @Resource
    private UserMapper userMapper;

    @Override
    public void convert(Menu form, HMenuVo to) {
        //设置用户名
        if (form.getUserId()!=null){
            to.setUserName(userMapper.selectByPrimaryKey(form.getUserId()).getUserName());
        }
        super.convert(form, to);
    }
}
