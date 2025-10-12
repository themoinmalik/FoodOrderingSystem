package com.user.user_service.controllers;


import com.user.user_service.auth.AuthService;
import com.user.user_service.auth.jwt.UserDetailserviceimpl;
import com.user.user_service.dtos.AuthRequest;
import com.user.user_service.dtos.AuthResponse;
import com.user.user_service.dtos.SignUpRequest;
import com.user.user_service.dtos.UserDto;
import com.user.user_service.models.User;
import com.user.user_service.repositories.UserRepo;
import com.user.user_service.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = Logger.getLogger(AuthController.class.getName());

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailserviceimpl userDetailserviceimpl;
    private final JwtUtil jwtutil;
    private final UserRepo userRepo;


    //create user...  //signUp
    @PostMapping("/signUp")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest signUpRequest) {
        log.info("User creating :  {}" + signUpRequest.getName());
        User user = authService.createUser(signUpRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    //login
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest,
                              HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Email or Password ");
        } catch (DisabledException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
            return null;
        }
        UserDetails userDetails = userDetailserviceimpl.loadUserByUsername(authRequest.getEmail());
        final String jwtToken = jwtutil.generateToken(userDetails.getUsername());
        Optional<User> optionalUser = userRepo.findByEmail(userDetails.getUsername());
        AuthResponse authResponse = new AuthResponse();
        if (optionalUser.isPresent()) {
            authResponse.setJwt(jwtToken);
            authResponse.setUserId(optionalUser.get().getId());
            authResponse.setRole(optionalUser.get().getRole());
        }
        log.info("User logged in: " + userDetails.getUsername());
        return authResponse;
    }

    @GetMapping("/validateToken")
    Boolean isTokenValid(@RequestHeader("Authorization") String token) {
        log.info("Validating token : " + token);
        String jwt = token.split(" ")[1].trim();
        String userEmail = jwtutil.extractUsername(jwt);
        UserDetails userDetails = userDetailserviceimpl.loadUserByUsername(userEmail);
        return jwtutil.isTokenValid(jwt, userDetails);
    }

    @GetMapping("/extractUserName")
    public String extractUserName(@RequestParam("jwtToken") String jwt) {
        log.info("Extracting user name : " + jwt);
        return jwtutil.extractUsername(jwt);
    }

    @GetMapping("/getUserByUsername")
    public UserDto getUserByUsername(@RequestParam("username") String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        UserDto userDto = new UserDto();
        assert user != null;
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setId(user.getId());
        userDto.setRole(user.getRole().toString());
        return userDto;
    }

    @GetMapping("/getUserById")
    UserDto getUserById(@RequestParam("userId") Long userId){

        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        log.info("userDto : " + userDto);
        return userDto;
    }


    //update user....
    @PostMapping("/updateUser")
    public User updateUser(@RequestBody SignUpRequest signUpRequest) {
        log.info("Updating user : " + signUpRequest.getName());
        return authService.updateUser(signUpRequest);
    }


    //Delete User...
    @PostMapping("/deleteById/{userId}")
    public User deleteById(@PathVariable Long userId) {
        log.info("Deleting user : " + userId);
        return authService.deleteUserById(userId);
    }


}
