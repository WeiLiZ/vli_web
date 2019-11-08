package com.vli.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.vli.converter.ClickingBarConverter;
import com.vli.mapper.ClickingBarMapper;
import com.vli.po.ClickingBar;
import com.vli.po.ModelPageInfo;
import com.vli.vo.ClickingBarVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ClickingBarService {

    @Resource
    private ClickingBarMapper clickingBarMapper;

    @Resource
    private ClickingBarConverter clickingBarConverter;

    public ModelPageInfo<ClickingBarVo> list() {
        Example example = new Example(ClickingBar.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus",Boolean.FALSE);
        Page<ClickingBar> page= PageHelper.startPage(1, 3);
        clickingBarMapper.selectByExample(example);
        List<ClickingBarVo> convert = clickingBarConverter.convert(page.getResult(), ClickingBarVo.class);
        ModelPageInfo<ClickingBarVo> modelPageInfo= new ModelPageInfo<>();
        modelPageInfo.setData(convert);
        modelPageInfo.setPage(page.getPageNum());
        modelPageInfo.setPageSize(page.getPageSize());
        modelPageInfo.setTotal(page.getTotal());
        return modelPageInfo;
    }
}
