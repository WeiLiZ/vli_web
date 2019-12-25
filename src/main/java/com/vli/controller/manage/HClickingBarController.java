package com.vli.controller.manage;

import com.vli.service.HClickingBarService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/vli/back/end/clicking/bar")
public class HClickingBarController {

    @Resource
    private HClickingBarService hClickingBarService;

}
