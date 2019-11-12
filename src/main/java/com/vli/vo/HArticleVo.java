package com.vli.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class HArticleVo {

    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String title;
    private String introduce;
    private String content;
    private Integer userId;
    private String userName;
    private Integer viewNum;
    private Integer weightNum;
    private Integer typeId;
    private String typeName;
    private String homePic;
}
