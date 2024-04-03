package com.zwyue.observe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.logging.log4j.message.Message;

public class ConcreteSubject implements Subject{

    private List<Consumer<Message>> consumers = new ArrayList<>() ;

    @Override
    public void registerObserver(Consumer<Message> consumer) {
        consumers.add(consumer);
    }

    @Override
    public void removeObserver(Consumer<Message> consumer) {
        consumers.remove(consumer);
    }

    @Override
    public void notifyObserver(Message message) {
        for (Consumer<Message> consumer : consumers) {
            consumer.accept(message);
        }
    }
}
