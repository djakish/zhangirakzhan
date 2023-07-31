package com.example.zhangirakzhan.controller;

import com.example.zhangirakzhan.dto.LoginDTO;
import com.example.zhangirakzhan.entity.User;
import com.example.zhangirakzhan.service.TokenService;
import com.example.zhangirakzhan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @Autowired
    public AuthController(
            UserService userService,
            AuthenticationManager authenticationManager,
            TokenService tokenService
    ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody User user) {
        this.userService.createUser(user);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, tokenService.generateToken(authentication))
                .body("Authorized!");
    }
}
