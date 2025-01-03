package com.zwyue;

import com.zwyue.config.KafkaConfig;
import com.zwyue.config.MySqlConfig;
import com.zwyue.config.RedisConfig;
import com.zwyue.behavior.observer.ConcreteSubject;
import org.apache.logging.log4j.message.FormattedMessage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@MapperScan("com.zwyue.dao")
public class DesignPatternApplication {

    public static final RedisConfig REDIS_CONFIG = new RedisConfig();

    public static final KafkaConfig KAFKA_CONFIG = new KafkaConfig();

    public static final MySqlConfig MY_SQL_CONFIG = new MySqlConfig();

    public static void main(String[] args) {
//        ScheduledUpdater redisConfigUpdator = new ScheduledUpdater(REDIS_CONFIG, 300, 300);
//        redisConfigUpdator.run();
//
//        ScheduledUpdater kafkaConfigUpdator = new ScheduledUpdater(KAFKA_CONFIG, 300, 300);
//        kafkaConfigUpdator.run();
//
//        SimpleHttpServer simpleHttpServer = new SimpleHttpServer("127.0.0.1",2389) ;
//        simpleHttpServer.addViewers("./config",REDIS_CONFIG);
//        simpleHttpServer.addViewers("./config",MY_SQL_CONFIG);
//
//        SpringApplication.run(DesignPatternApplication.class, args);

        ConcreteSubject concreteSubject = new ConcreteSubject() ;
        concreteSubject.registerObserver(System.out::println);
        concreteSubject.registerObserver(System.out::println);
        concreteSubject.notifyObserver(new FormattedMessage("......"));
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/observe")
    public void observe() {
        ConcreteSubject concreteSubject = new ConcreteSubject() ;
        concreteSubject.registerObserver(System.out::println);
        concreteSubject.registerObserver(System.out::println);
        concreteSubject.notifyObserver(new FormattedMessage("......"));
    }
}
