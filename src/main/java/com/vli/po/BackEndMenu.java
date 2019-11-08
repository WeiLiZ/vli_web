package com.vli.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "v_back_end_menu")
@Data
public class BackEndMenu  extends Currency{

    @Column(name = "name")
    private String name;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name="site")
    private String site;
    @Column(name = "update_time")
    private Date updateTime;
}
