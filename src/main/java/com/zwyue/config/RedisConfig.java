package com.zwyue.config;

import com.zwyue.interfaces.Updator;
import com.zwyue.interfaces.Viewer;
import java.util.Map;
import lombok.Data;

@Data
public class RedisConfig implements Updator , Viewer {

    private String address ;

    private int timeout ;

    private int maxTotal ;

    @Override
    public void update() {}

    @Override
    public String outputInPlainTxt() {
        return null;
    }

    @Override
    public Map<String, String> output() {
        return null;
    }
}
