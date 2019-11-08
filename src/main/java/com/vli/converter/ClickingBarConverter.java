package com.vli.converter;

import com.vli.po.ClickingBar;
import com.vli.utlis.BaseConverter;
import com.vli.vo.ClickingBarVo;
import org.springframework.stereotype.Component;

@Component
public class ClickingBarConverter  extends BaseConverter<ClickingBar, ClickingBarVo> {

    @Override
    public void convert(ClickingBar from, ClickingBarVo to) {
        super.convert(from, to);
    }
}
