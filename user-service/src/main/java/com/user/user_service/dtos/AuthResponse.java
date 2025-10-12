package com.user.user_service.dtos;


import com.user.user_service.constants.ROLES;
import lombok.Data;

@Data
public class AuthResponse {


    private String jwt;
    private ROLES Role;
    private Long userId;


}
