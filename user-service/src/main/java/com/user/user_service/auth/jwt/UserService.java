package com.user.user_service.auth.jwt;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDetailsService userDetailservice();

}
