package com.user.user_service.dtos;


import com.user.user_service.constants.ROLES;
import lombok.Data;

@Data
public class SignUpRequest {

    private String name;

    private String email;

    private String password;

    private ROLES role;


}
