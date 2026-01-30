package com.example.user_service.controller;


import com.example.user_service.dto.CreateRequestDto;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${kafka.topic.user-topic}")
    private String KAFKA_RANDOM_USER_TOPIC;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UserService userService;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        for (int i = 0; i < 1000; i++)
            kafkaTemplate.send(KAFKA_RANDOM_USER_TOPIC, "" + i % 3, message + i);
        log.info("Sent message: {}", message);
        return ResponseEntity.ok("Message is sent to Kafka Topic Successfully");
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateRequestDto createRequestDto) {
        userService.createUser(createRequestDto);
        return ResponseEntity.ok("User creation requested is processed Successfully");

    }


}
