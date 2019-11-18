package com.vli.vo;

import com.vli.po.Currency;
import lombok.Data;

import java.util.List;

@Data
public class ClickingBarVo extends Currency {


    private String name;

    private List<String> imgs;
}
