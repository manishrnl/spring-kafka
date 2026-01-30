package com.example.notification_service.consumer;


import com.example.notification_service.events.UserCreatedEvents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserKafkaConsumer {

    @KafkaListener(topics = "user-created-topic", groupId = "notification-group")
    public void handleUserCreatedTopic(UserCreatedEvents userCreatedEvents) {
        log.info("handleUserCreatedTopic: User Created Event Received: ID = {}, Email = {}", userCreatedEvents.getId(), userCreatedEvents.getEmail());
    }


    @KafkaListener(topics = "user-topic")
    public void handleUserRandomTopic1(String message) {

        log.info("handleUserRandomTopic1: Message Received {}", message);
    }

    @KafkaListener(topics = "user-topic")
    public void handleUserRandomTopic2(String message) {

        log.info("handleUserRandomTopic2: {}", message);
    }

    @KafkaListener(topics = "user-topic")
    public void handleUserRandomTopic3(String message) {

        log.info("handleUserRandomTopic3: {}", message);
    }

}
