package com.example.user_service.events;

import lombok.Data;

@Data
public class UserCreatedEvents {


    private Long id;
    private String email;
}
