package com.user.user_service.dtos;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemDto {

    private Long id;
    private String name;
    private String category;

}
