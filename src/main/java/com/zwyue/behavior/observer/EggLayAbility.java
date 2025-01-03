package com.zwyue.behavior.observer;

import com.zwyue.interfaces.EggLayAble;

public class EggLayAbility implements EggLayAble {
    @Override
    public void layEgg() {
        System.out.println("...... lay egg ......");
    }
}
