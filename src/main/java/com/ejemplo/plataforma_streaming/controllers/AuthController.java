package com.ejemplo.plataforma_streaming.controllers;

import com.ejemplo.plataforma_streaming.dto.AuthRequest;
import com.ejemplo.plataforma_streaming.dto.AuthResponse;
import com.ejemplo.plataforma_streaming.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    //Login endpoint, receives a POST request with an AuthRequest object and returns an AuthResponse object with a JWT token
    @PostMapping("/login")
    public AuthResponse createToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or password");
        }

        final UserDetails userDetails = (UserDetails) authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
                )
                .getPrincipal();

        final String jwtToken = jwtUtil.generateToken(userDetails);



        return new AuthResponse(jwtToken, userDetails.getUsername());
    }
}
