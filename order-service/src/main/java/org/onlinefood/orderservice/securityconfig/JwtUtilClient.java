package org.onlinefood.orderservice.securityconfig;


import org.onlinefood.orderservice.dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface JwtUtilClient {

    @GetMapping("/api/auth/validateToken")
    Boolean isTokenValid(@RequestHeader("Authorization") String token);

    @GetMapping("/api/auth/extractUserName")
    String extractUserName(@RequestParam("jwtToken") String jwtToken);


    @GetMapping("/api/auth/getUserByUsername")
    UserDto getUserByUsername(@RequestParam("username") String username);

    @GetMapping("/api/auth/getUserById")
    UserDto getUserById(@RequestParam("userId") Long userId);
}
