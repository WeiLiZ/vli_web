package com.vli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vli/back/end")
public class HIndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/back/end/menu/list")
    public String backEndMenuList() {
        return "backEndMenu/back_end_menu_list";
    }

    @GetMapping("/back/end/user/list")
    public String backEndUserList() {
        return "user/user_list";
    }

    @GetMapping("/tips")
    public String tips() {
        return "tips";
    }

}
