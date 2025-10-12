package org.onlinefood.restaurant.dtos;


import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String password;
    private String email;
    private String role;

}
