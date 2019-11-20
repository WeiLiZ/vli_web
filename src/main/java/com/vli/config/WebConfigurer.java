package com.vli.config;


import com.vli.config.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                //静态
                .excludePathPatterns("/js/**")//静态资源
                .excludePathPatterns("/css/**")//静态资源
                .excludePathPatterns("/img/**")//静态资源
                //前端
                .excludePathPatterns("/vli/user/login")//用户登陆
                .excludePathPatterns("/vli/user/register")//用户注册
                .excludePathPatterns("/vli/user/getQqInformation")//查询QQ信息
                .excludePathPatterns("/vli/comment/subComment")//提交评论
                .excludePathPatterns("/vli/article/list")//文章查询
                .excludePathPatterns("/vli/article/findArticleById")//文章详情
                .excludePathPatterns("/vli/clicking/bar/list")//点击栏
                //后端
                .excludePathPatterns("/vli/back/end/tips")//提示页面
                .excludePathPatterns("/vli/back/end/login");//后台登陆页面
    }
}