package com.zwyue.behavior.observer;

import com.zwyue.interfaces.TweetAble;

public class TweetAbility implements TweetAble {
    @Override
    public void tweet() {
        System.out.println("...... tweet ......");
    }
}
