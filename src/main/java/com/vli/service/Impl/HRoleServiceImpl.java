package com.vli.service.Impl;

import com.vli.converter.HRoleConverter;
import com.vli.mapper.RoleMapper;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.po.Role;
import com.vli.service.HRoleService;
import com.vli.vo.HRoleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class HRoleServiceImpl implements HRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private HRoleConverter hRoleConverter;

    @Override
    public ResultModel list() {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus",Boolean.FALSE);
        List<Role> roles = roleMapper.selectByExample(example);
        List<HRoleVo> convert = hRoleConverter.convert(roles, HRoleVo.class);
        return ResultModel.success(ResultCode.SUCCESS,convert);
    }
}
