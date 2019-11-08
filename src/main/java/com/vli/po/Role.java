package com.vli.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "v_role")
public class Role extends Currency {

    @Column(name = "role_name")
    private String roleName;
}
