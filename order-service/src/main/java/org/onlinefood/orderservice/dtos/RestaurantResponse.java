package org.onlinefood.orderservice.dtos;


import lombok.Data;

@Data
public class RestaurantResponse {

    public Long id;

    public String name;

    public String location;

    public String contactInfo;

    public String profileId;

}
