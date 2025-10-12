package org.onlinefood.orderservice.dtos;


import lombok.Data;

@Data
public class UserDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private String role;


}
