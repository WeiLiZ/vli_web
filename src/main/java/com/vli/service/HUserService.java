package com.vli.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.mapper.RoleMapper;
import com.vli.mapper.UserMapper;
import com.vli.parameter.HUserParameter;
import com.vli.po.ModelPageInfo;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.po.User;
import com.vli.utlis.BaseConverter;
import com.vli.utlis.MD5;
import com.vli.vo.HUserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class HUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    public ModelPageInfo list() {
        Page<User> page = PageHelper.startPage(1, 10);
        Example example = new Example(User.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus", Boolean.FALSE);
        List<User> users = userMapper.selectByExample(example);
        BaseConverter baseConverter = new BaseConverter();
        List<HUserVo> convert = baseConverter.convert(users, HUserVo.class);
        convert.forEach(query -> {
            if (query.getRoleId() != null) {
                query.setRoleName(roleMapper.selectByPrimaryKey(query.getRoleId()).getRoleName());
            }
        });
        ModelPageInfo<HUserVo> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setData(convert);
        modelPageInfo.setPage(page.getPageNum());
        modelPageInfo.setPageSize(page.getPageSize());
        modelPageInfo.setTotal(page.getTotal());
        return modelPageInfo;
    }

    public ResultModel add(HUserParameter params) {
        Example example = new Example(User.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus", Boolean.FALSE);
        criteria.andEqualTo("userName", params.getUserName());
        criteria.andEqualTo("phone", params.getPhone());
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty()) {
            BaseConverter baseConverter = new BaseConverter();
            User convert = (User) baseConverter.convert(params, User.class);
            convert.setDeleteStatus(Boolean.FALSE);
            convert.setUpdateTime(new Date());
            convert.setCreateTime(new Date());
            convert.setPassword(MD5.getMD5(convert.getPassword()));
            userMapper.insert(convert);
            return ResultModel.success();
        }
        return ResultModel.failure(ResultCode.USER_HAS_USERNAME_OR_PHONE);
    }

    public ResultModel update(HUserParameter params) {
        BaseConverter baseConverter = new BaseConverter();
        User convert = (User) baseConverter.convert(params, User.class);
        convert.setUpdateTime(new Date());
        userMapper.updateByPrimaryKeySelective(convert);
        return ResultModel.success();
    }

    public ResultModel delete(HUserParameter params) {
        User user = new User();
        user.setId(params.getId());
        user.setDeleteStatus(Boolean.TRUE);
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        return ResultModel.success();
    }
}
