package com.zwyue;

import com.zwyue.config.KafkaConfig;
import com.zwyue.config.MySqlConfig;
import com.zwyue.config.RedisConfig;
import com.zwyue.server.SimpleHttpServer;
import com.zwyue.timeTask.ScheduledUpdater;

public class DesignPatternApplication {

    public static final RedisConfig REDIS_CONFIG = new RedisConfig();

    public static final KafkaConfig KAFKA_CONFIG = new KafkaConfig();

    public static final MySqlConfig MY_SQL_CONFIG = new MySqlConfig();

    public static void main(String[] args) {
        ScheduledUpdater redisConfigUpdator = new ScheduledUpdater(REDIS_CONFIG, 300, 300);
        redisConfigUpdator.run();

        ScheduledUpdater kafkaConfigUpdator = new ScheduledUpdater(KAFKA_CONFIG, 300, 300);
        kafkaConfigUpdator.run();

        SimpleHttpServer simpleHttpServer = new SimpleHttpServer("127.0.0.1",2389) ;
        simpleHttpServer.addViewers("./config",REDIS_CONFIG);
        simpleHttpServer.addViewers("./config",MY_SQL_CONFIG);
    }
}
