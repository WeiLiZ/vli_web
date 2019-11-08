package com.vli.converter;

import com.vli.po.Role;
import com.vli.utlis.BaseConverter;
import com.vli.vo.HRoleVo;
import org.springframework.stereotype.Component;

@Component
public class HRoleConverter extends BaseConverter<Role, HRoleVo> {

    @Override
    public void convert(Role form, HRoleVo to) {
        super.convert(form, to);
    }
}
