package com.user.user_service.auth;


import com.user.user_service.dtos.SignUpRequest;
import com.user.user_service.models.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    User createUser(SignUpRequest signUpRequest);

    User updateUser(SignUpRequest signUpRequest);

    User deleteUserById(Long userId);
}
