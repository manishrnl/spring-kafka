package com.example.user_service.service;

import com.example.user_service.dto.CreateRequestDto;
import com.example.user_service.entity.UserEntity;
import com.example.user_service.events.UserCreatedEvents;
import com.example.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long, UserCreatedEvents> kafkaTemplate;

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;


    public void createUser(CreateRequestDto createRequestDto) {
        UserEntity userEntity = modelMapper.map(createRequestDto, UserEntity.class);
        UserEntity savedUser = userRepository.save(userEntity);

//      Publishing  User Created Event to Kafka
        UserCreatedEvents userCreatedEvents = modelMapper.map(savedUser, UserCreatedEvents.class);
        kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC, userCreatedEvents.getId(), userCreatedEvents);

    }
}
