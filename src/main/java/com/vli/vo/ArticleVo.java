package com.vli.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ArticleVo {

    private Integer id;//id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;//创建时间

    private String title;//标题

    private String introduce;//介绍

    private String content;//内容

    private Integer userId;//用户id

    private String userName;//用户名称

    private Integer viewNum;//浏览量

    private Integer commentNum;//评论量

    private Integer typeId;//类型id

    private String homePic;//图片
}
