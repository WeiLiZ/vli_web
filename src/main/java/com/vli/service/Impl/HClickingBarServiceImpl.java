package com.vli.service.Impl;

import com.vli.mapper.ClickingBarMapper;
import com.vli.service.HClickingBarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class HClickingBarServiceImpl implements HClickingBarService {

    @Resource
    private ClickingBarMapper clickingBarMapper;

}
