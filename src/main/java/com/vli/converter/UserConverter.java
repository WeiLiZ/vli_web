package com.vli.converter;

import com.vli.vo.UserVo;
import com.vli.po.User;
import com.vli.utlis.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends BaseConverter<User, UserVo> {

    @Override
    public void convert(User from, UserVo to) {
        super.convert(from, to);
    }
}
