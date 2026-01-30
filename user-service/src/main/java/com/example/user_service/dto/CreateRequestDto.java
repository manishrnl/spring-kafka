package com.example.user_service.dto;

import lombok.Data;

@Data
public class CreateRequestDto {
    private Long id;
    private String name;
    private String email;
}
