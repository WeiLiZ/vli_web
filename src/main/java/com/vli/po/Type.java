package com.vli.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "v_type")
@Data
public class Type extends Currency {

    @Column(name ="type_name")
    private String typeName;

    @Column(name = "user_id")
    private Integer userId;
}
