package com.vli.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vli/back/end")
public class HIndexController {

    /**
     * 首页
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 登陆页面
     * @return
     */
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    /**
     * 首页欢迎页
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * 没有权限提示页
     * @return
     */
    @GetMapping("/tips")
    public String tips() {
        return "tips";
    }

    /**
     * 管理后端菜单页面
     * @return
     */
    @GetMapping("/back/end/menu/list")
    public String backEndMenuList() {
        return "backEndMenu/back_end_menu_list";
    }

    /**
     * 管理用户页面
     * @return
     */
    @GetMapping("/back/end/user/list")
    public String backEndUserList() {
        return "user/user_list";
    }

    /**
     * 文章管理页面
     * @return
     */
    @GetMapping("/back/end/article/list")
    public String articleList() {
        return "article/article_list";
    }

    /**
     * 点击栏展示管理
     * @return
     */
    @GetMapping("/back/end/clicking/bar/list")
    public String clickingBarList() {
        return "clickingBar/clicking_bar_list";
    }

}
