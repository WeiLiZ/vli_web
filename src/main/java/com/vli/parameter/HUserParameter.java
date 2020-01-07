package com.vli.parameter;

import lombok.Data;

@Data
public class HUserParameter {

    private Integer id;//id

    private String userName;//用户名

    private String phone;//手机号

    private String sex;//性别

    private Integer age;//年龄

    private Integer roleId;//权限id

    private String password;//密码

    private String healPortrait;//头像地址路径
}
