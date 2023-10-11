package com.zwyue.config;

import com.zwyue.interfaces.Viewer;
import java.util.Map;

public class MySqlConfig implements Viewer {
    @Override
    public String outputInPlainTxt() {
        return null;
    }

    @Override
    public Map<String, String> output() {
        return null;
    }
}
