package com.vli.parameter;

import lombok.Data;

@Data
public class HArticleParameter {

    private Integer id;
    private String  title;
    private String  introduce;
    private Integer viewNum;
    private Integer weightNum;
    private String content;
    private Integer typeId;
    private String  homePic;
}
