package com.zwyue.behavior;

import com.zwyue.interfaces.EggLayAble;
import jakarta.servlet.http.HttpServlet;
import java.io.InputStream;
import java.util.AbstractList;
import org.apache.catalina.core.ApplicationFilterChain;

public class EggLayAbility implements EggLayAble {
    @Override
    public void layEgg() {
        System.out.println("...... lay egg ......");
    }
}
