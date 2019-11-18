package com.vli.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "v_article")
@Data
public class Article extends Currency {

    @Column(name = "title")
    private String title;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "weight_num")
    private Integer weightNum;

    @Column(name = "view_num")
    private Integer viewNum;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "home_pic")
    private String homePic;

    @Column(name = "clicking_bar_id")
    private Integer clickingBarId;

    @Column(name = "update_time")
    private Date updateTime;

}
