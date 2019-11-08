package com.vli.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "v_clicking_bar")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClickingBar extends Currency {

    @Column(name = "name")
    private String name;

}
