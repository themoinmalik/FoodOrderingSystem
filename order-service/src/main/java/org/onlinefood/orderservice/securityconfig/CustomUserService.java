package org.onlinefood.orderservice.securityconfig;


import lombok.extern.slf4j.Slf4j;
import org.onlinefood.orderservice.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Slf4j
@Service
public class CustomUserService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(CustomUserService.class.getName());

    @Autowired
    private JwtUtilClient jwtUtil;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto user = jwtUtil.getUserByUsername(username);

        log.info("user get from user -service{}", user.toString());

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }
}
