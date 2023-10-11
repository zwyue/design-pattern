package com.zwyue.behavior;

import com.zwyue.interfaces.TweetAble;

public class TweetAbility implements TweetAble {
    @Override
    public void tweet() {
        System.out.println("...... tweet ......");
    }
}
