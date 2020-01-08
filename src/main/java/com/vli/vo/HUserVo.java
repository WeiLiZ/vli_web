package com.vli.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class HUserVo {

    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Boolean deleteStatus;
    private String userName;
    private String phone;
    private String sex;
    private Integer age;
    private Integer roleId;
    private String  roleName;
    private String healPortrait;
    private Integer qqNumber;
    private String mailbox;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
