package com.zwyue.bird;

import com.zwyue.behavior.EggLayAbility;
import com.zwyue.behavior.TweetAbility;
import com.zwyue.interfaces.EggLayAble;
import com.zwyue.interfaces.TweetAble;

public class Ostrich implements TweetAble, EggLayAble {
    TweetAbility tweetAbility = new TweetAbility();
    EggLayAbility eggLayAbility = new EggLayAbility();

    @Override
    public void layEgg() {
        eggLayAbility.layEgg();
    }

    @Override
    public void tweet() {
        tweetAbility.tweet();
    }
}
