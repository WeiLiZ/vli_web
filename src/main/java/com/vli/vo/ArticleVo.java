package com.vli.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ArticleVo {

    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String title;
    private String introduce;
    private String content;
    private Integer userId;
    private String userName;
    private Integer viewNum;
    private Integer typeId;
    private String homePic;
}
