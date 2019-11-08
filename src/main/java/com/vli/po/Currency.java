package com.vli.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;


@Data
public class Currency {

    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "delete_status")
    private Boolean deleteStatus;
}
