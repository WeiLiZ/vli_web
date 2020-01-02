package com.vli.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author ZL
 * Created on 2019/12/31.
 */
@Data
@Table(name = "v_menu")
public class Menu extends Currency {

    @Column(name = "user_id")
    private Integer userId;

    private String name;

    private String url;

}
