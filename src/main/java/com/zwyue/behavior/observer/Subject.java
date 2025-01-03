package com.zwyue.behavior.observer;

import java.util.function.Consumer;
import org.apache.logging.log4j.message.Message;

public interface Subject {

    void registerObserver(Consumer<Message> consumer);
    void removeObserver(Consumer<Message> consumer);
    void notifyObserver(Message message);

}
