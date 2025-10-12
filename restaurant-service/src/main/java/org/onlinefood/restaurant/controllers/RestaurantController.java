package org.onlinefood.restaurant.controllers;

import org.onlinefood.restaurant.dtos.RestaurantDTO;
import org.onlinefood.restaurant.models.Restaurant;
import org.onlinefood.restaurant.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    public static final Logger log = Logger.getLogger(RestaurantController.class.getName());

    @Autowired
    private RestaurantService restaurantService;


    @PostMapping
    public Restaurant createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        log.info("Creating restaurant " + restaurantDTO.getName());
        return restaurantService.createRestaurant(restaurantDTO);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) {
        log.info("Retrieving restaurant " + restaurantId);
        Optional<Restaurant> restaurant = restaurantService.getRestaurant(restaurantId);
        return restaurant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long restaurantId,
                                                       @RequestBody RestaurantDTO restaurantDetails) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(restaurantId, restaurantDetails);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }
}
