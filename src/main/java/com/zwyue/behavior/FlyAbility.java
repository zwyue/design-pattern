package com.zwyue.behavior;

import com.zwyue.interfaces.FlyAble;

public class FlyAbility implements FlyAble {
    @Override
    public void fly() {
        System.out.println("...... fly ......");
    }
}
