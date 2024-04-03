package com.zwyue.observe;

import java.util.function.Consumer;
import java.util.function.Function;
import org.apache.logging.log4j.message.Message;

public interface Subject {

    void registerObserver(Consumer<Message> consumer);
    void removeObserver(Consumer<Message> consumer);
    void notifyObserver(Message message);

}
