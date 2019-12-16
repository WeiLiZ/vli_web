package com.vli.converter;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.vli.po.ModelPageInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModelPageInfoConvert {

    public <T> ModelPageInfo<T> convertPage(Page<T> page){
        ModelPageInfo<T> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setPage(page.getPageNum());
        modelPageInfo.setPageSize(page.getPageSize());
        modelPageInfo.setTotal(page.getTotal());
        modelPageInfo.setData(page.getResult());
        return modelPageInfo;
    }
    public <T> ModelPageInfo<T> convertPage(Page<T> page, List<T> convert){
        ModelPageInfo<T> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setPage(page.getPageNum());
        modelPageInfo.setPageSize(page.getPageSize());
        modelPageInfo.setTotal(page.getTotal());
        modelPageInfo.setData(convert);
        return modelPageInfo;
    }

    public <T,S> ModelPageInfo<S> convertDifferentPages(Page<T> page, List<S> convert){
        ModelPageInfo<S> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setPage(page.getPageNum());
        modelPageInfo.setPageSize(page.getPageSize());
        modelPageInfo.setTotal(page.getTotal());
        modelPageInfo.setData(convert);
        return modelPageInfo;
    }

    public <T> ModelPageInfo<T> convertPageInfo(PageInfo<T> pageInfo){
        ModelPageInfo<T> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setPage(pageInfo.getPageNum());
        modelPageInfo.setPageSize(pageInfo.getPageSize());
        modelPageInfo.setTotal(pageInfo.getTotal());
        modelPageInfo.setData(pageInfo.getList());
        return modelPageInfo;
    }

    public <T> ModelPageInfo<T> convertPageInfo(PageInfo<T> pageInfo,List<T> convert){
        ModelPageInfo<T> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setPage(pageInfo.getPageNum());
        modelPageInfo.setPageSize(pageInfo.getPageSize());
        modelPageInfo.setTotal(pageInfo.getTotal());
        modelPageInfo.setData(convert);
        return modelPageInfo;
    }

    public <T,S> ModelPageInfo<S> convertDifferentPageInfo(PageInfo<T> pageInfo,List<S> convert){
        ModelPageInfo<S> modelPageInfo = new ModelPageInfo<>();
        modelPageInfo.setPage(pageInfo.getPageNum());
        modelPageInfo.setPageSize(pageInfo.getPageSize());
        modelPageInfo.setTotal(pageInfo.getTotal());
        modelPageInfo.setData(convert);
        return modelPageInfo;
    }
}

