package com.user.user_service.auth;

import com.user.user_service.dtos.SignUpRequest;
import com.user.user_service.exceptions.UserAlreadyExits;
import com.user.user_service.models.User;
import com.user.user_service.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class AuthServiceImpl implements AuthService{

    public static Logger log = Logger.getLogger(AuthServiceImpl.class.getName());

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public User createUser(SignUpRequest signUpRequest) {

        //check if user already exist or not.
        log.info("user creating : 2");
        userRepo.findByEmail(signUpRequest.getEmail()).ifPresent(user -> {
            throw new UserAlreadyExits("User already exists with email " + user.getEmail());
        });
        User newUser = new User();
        newUser.setName(signUpRequest.getName());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        newUser.setRole(signUpRequest.getRole());
        return userRepo.save(newUser);
    }

    @Override
    public User updateUser(SignUpRequest signUpRequest) {
        User user = userRepo.findByEmail(signUpRequest.getEmail()).orElse(null);
        log.info("user updating : 2");
        if (user != null) {
            user.setName(signUpRequest.getName());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        }
        assert user != null;
        return userRepo.save(user);
    }

    @Override
    public User deleteUserById(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        assert user != null;
        userRepo.delete(user);
        log.info("user deleting : 2");
        return user;
    }
}
