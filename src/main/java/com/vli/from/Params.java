package com.vli.from;

import lombok.Data;

import java.util.Map;

/**
 * @author ZL
 * Created on 2020/1/2.
 */
@Data
public class Params {

    private Integer page;
    private Integer pageSize;

    private Map<String, Object> map;


}
