package org.onlinefood.restaurant.services;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.onlinefood.restaurant.repos.ProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    @Autowired
    private final ProfileRepo profileRepo;



}
