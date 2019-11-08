package com.vli.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "v_user")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {

    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Boolean deleteStatus;
    private String  userName;
    private String  phone;
    private String  sex;
    private Integer  age;
    private Integer roleId;
    private String  healPortrait;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date  updateTime;
}

