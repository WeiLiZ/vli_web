package com.vli.service;

import com.vli.mapper.ClickingBarMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class HClickingBarService {

    @Resource
    private ClickingBarMapper clickingBarMapper;

}
