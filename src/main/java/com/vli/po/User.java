package com.vli.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "v_user")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends Currency implements Serializable {

    @Column(name = "user_name")
    private String  userName;
    @Column(name = "password")
    private String  password;
    @Column(name = "phone")
    private String  phone;
    @Column(name = "sex")
    private String  sex;
    @Column(name = "age")
    private Integer  age;
    @Column(name = "login_ip")
    private String loginIp;
    @Column(name = "qq_number")
    private Integer qqNumber;
    @Column(name = "mailbox")
    private String mailbox;
    @Column(name = "qq_heal_portrait")
    private String qqHealPortrait;
    @Column(name = "heal_portrait")
    private String  healPortrait;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "update_time")
    private Date  updateTime;
}
