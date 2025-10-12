package org.onlinefood.restaurant.dtos;


import lombok.Data;
import org.onlinefood.restaurant.models.Profile;

@Data
public class RestaurantDTO {


    private String name;
    private String location;
    private String contactInfo;
    private Profile profile;



}
