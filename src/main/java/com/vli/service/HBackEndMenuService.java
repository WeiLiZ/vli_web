package com.vli.service;

import com.github.pagehelper.PageInfo;
import com.vli.converter.HBackEndMenuConverter;
import com.vli.converter.ModelPageInfoConvert;
import com.vli.mapper.BackEndMenuMapper;
import com.vli.parameter.HBackEndMenuParameter;
import com.vli.po.*;
import com.vli.utlis.BaseConverter;
import com.vli.vo.HBackEndMenuVo;
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
public class HBackEndMenuService {

    @Resource
    private BackEndMenuMapper backEndMenuMapper;

    @Resource
    private HBackEndMenuConverter hBackEndMenuConverter;

    @Resource
    private ModelPageInfoConvert modelPageInfoConvert;


    public ModelPageInfo<HBackEndMenuVo> list() {
        Example example = new Example(BackEndMenu.class);
        example.setOrderByClause("create_time ASC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus",Boolean.FALSE);
        List<BackEndMenu> backEndMenus = backEndMenuMapper.selectByExample(example);
        List<HBackEndMenuVo> convert = hBackEndMenuConverter.convert(backEndMenus, HBackEndMenuVo.class);
        PageInfo<HBackEndMenuVo> backEndMenuPageInfo = new PageInfo<>(convert);
        ModelPageInfo<HBackEndMenuVo> modelPageInfo = modelPageInfoConvert.convertPageInfo(backEndMenuPageInfo, convert);
        return modelPageInfo;
    }

    public ResultModel add(HttpServletRequest request,HBackEndMenuParameter params) {
        Example example = new Example(BackEndMenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus",Boolean.FALSE);
        criteria.andEqualTo("name",params.getName());
        List<BackEndMenu> backEndMenus = backEndMenuMapper.selectByExample(example);
        ResultModel<Object> resultModel = new ResultModel<>();
        if (!backEndMenus.isEmpty()){
            resultModel.setCode(ResultCode.FAILURE.code());
            resultModel.setMsg("添加的菜单名已经存在");
            resultModel.setResultCode(ResultCode.FAILURE);
            return resultModel;
        }
        BaseConverter baseConverter = new BaseConverter();
        BackEndMenu convert = (BackEndMenu) baseConverter.convert(params, BackEndMenu.class);
        convert.setUpdateTime(new Date());
        convert.setCreateTime(new Date());
        convert.setDeleteStatus(Boolean.FALSE);
        UserVo user = (UserVo) request.getSession().getAttribute("user");
        convert.setUserId(user.getId());
        backEndMenuMapper.insert(convert);
        return ResultModel.success();
    }

    public ResultModel update(HBackEndMenuParameter params) {
        BackEndMenu backEndMenu = new BackEndMenu();
        backEndMenu.setUpdateTime(new Date());
        backEndMenu.setName(params.getName());
        backEndMenu.setSite(params.getSite());
        backEndMenu.setId(params.getId());
        backEndMenuMapper.updateByPrimaryKeySelective(backEndMenu);
        return ResultModel.success();
    }

    public ResultModel delete(HBackEndMenuParameter params) {
        BackEndMenu backEndMenu = new BackEndMenu();
        backEndMenu.setId(params.getId());
        backEndMenu.setDeleteStatus(Boolean.TRUE);
        backEndMenu.setUpdateTime(new Date());
        backEndMenuMapper.updateByPrimaryKeySelective(backEndMenu);
        return ResultModel.success();
    }
}
