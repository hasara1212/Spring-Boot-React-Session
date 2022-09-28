package com.hasara.spring.security.controller;

import com.hasara.spring.security.dtos.LoginRequest;
import com.hasara.spring.security.service.AuthenticationService;
import com.hasara.spring.security.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationService authenticationService;
    private final JWTUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, AuthenticationService authenticationService, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("login")
    public String getLoginPage(@RequestBody LoginRequest loginRequest)
    {
       UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());
        authenticationManager.authenticate(token);

        UserDetails user = authenticationService.loadUserByUsername(loginRequest.getUsername());
        return jwtUtil.generateToken(user);



    }
}
