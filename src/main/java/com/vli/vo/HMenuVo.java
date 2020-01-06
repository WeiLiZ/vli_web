package com.vli.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author ZL
 * Created on 2020/1/6.
 */
@Data
public class HMenuVo {

    private Integer id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer userId;

    private String userName;

    private String name;

    private String url;
}
