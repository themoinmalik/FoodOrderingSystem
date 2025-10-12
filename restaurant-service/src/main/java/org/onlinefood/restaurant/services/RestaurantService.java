package org.onlinefood.restaurant.services;

import org.onlinefood.restaurant.dtos.RestaurantDTO;
import org.onlinefood.restaurant.exceptions.ResourceNotFoundException;
import org.onlinefood.restaurant.models.Restaurant;
import org.onlinefood.restaurant.repos.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RestaurantService {

    private static final Logger logger = Logger.getLogger(RestaurantService.class.getName());


    @Autowired
    private RestaurantRepo restaurantRepo;

    public Restaurant createRestaurant(RestaurantDTO restaurantDTO) {
        logger.info("Creating restaurant " + restaurantDTO.getName());

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setLocation(restaurantDTO.getLocation());
        restaurant.setContactInfo(restaurantDTO.getContactInfo());
        restaurant.setProfile(restaurantDTO.getProfile());

        return restaurantRepo.save(restaurant);
    }

    public Optional<Restaurant> getRestaurant(Long id) {
        logger.info("Retrieving restaurant " + id);
        return restaurantRepo.findById(id);
    }

    public Restaurant updateRestaurant(Long id, RestaurantDTO restaurantDetails) {
        logger.info("Updating restaurant " + id);
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        restaurant.setName(restaurantDetails.getName());
        restaurant.setLocation(restaurantDetails.getLocation());
        restaurant.setContactInfo(restaurantDetails.getContactInfo());
        restaurant.setProfile(restaurantDetails.getProfile());
        return restaurantRepo.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        logger.info("Deleting restaurant " + id);
        restaurantRepo.deleteById(id);
    }
}

