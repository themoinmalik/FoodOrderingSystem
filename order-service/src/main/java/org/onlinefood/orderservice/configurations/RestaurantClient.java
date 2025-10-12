package org.onlinefood.orderservice.configurations;


import org.onlinefood.orderservice.dtos.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service", url = "http://localhost:8082")
public interface RestaurantClient {


    @GetMapping("/api/restaurants/{restaurantId}")
    RestaurantResponse getRestaurant(@PathVariable("restaurantId") Long restaurantId);


}
