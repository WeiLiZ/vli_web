package com.vli.service;

import com.vli.converter.UserConverter;
import com.vli.mapper.UserMapper;
import com.vli.parameter.UserParameter;
import com.vli.po.ResultCode;
import com.vli.po.ResultModel;
import com.vli.po.User;
import com.vli.utlis.IpUtil;
import com.vli.utlis.MD5;
import com.vli.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private UserConverter userConverter;

    public Boolean insert(UserParameter userParameter) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone", userParameter.getPhone());
        criteria.orEqualTo("userName", userParameter.getUserName());
        List<User> users = userMapper.selectByExample(example);
        if (!users.isEmpty()) {
            return false;
        }
        User user = new User();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setDeleteStatus(Boolean.FALSE);
        user.setUserName(userParameter.getUserName());
        user.setPhone(userParameter.getPhone());
        user.setPassword(MD5.getMD5(userParameter.getPassword()));
        user.setLoginIp(IpUtil.getUserIP(request));
        user.setRoleId(3);
        userMapper.insert(user);
        return true;
    }

    public ResultModel login(String userName, String password, String userIP) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName", userName);
        List<User> users = userMapper.selectByExample(example);
        if (!users.isEmpty()) {
            User user = users.get(0);
            if (!user.getDeleteStatus()) {
                String pwd = MD5.getMD5(password);
                if (user.getPassword().equals(pwd)) {
                    this.updateLoginIp(user.getId(), userIP);
                    UserVo convert = userConverter.convert(user, UserVo.class);
                    return ResultModel.success(ResultCode.SUCCESS, convert);
                } else {
                    return ResultModel.failure(ResultCode.USER_LOGIN_ERROR, null);
                }
            } else {
                return ResultModel.failure(ResultCode.USER_ACCOUNT_FORBIDDEN, null);
            }
        } else {
            return ResultModel.failure(ResultCode.USER_NOT_EXIST, null);
        }
    }

    public void updateLoginIp(Integer id, String ip) {
        User user = userMapper.selectByPrimaryKey(id);
        user.setLoginIp(ip);
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKey(user);

    }
}
