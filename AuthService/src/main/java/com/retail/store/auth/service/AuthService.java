package com.retail.store.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {


    private final  JwtService jwtService;


    private final AuthenticationManager authenticationManager;



    public String authenticate(String username, String password) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authenticate.isAuthenticated()) {
            log.trace("user {} authenticated", username);
            return generateToken(username);
        } else {
            log.trace("invalid access for user {}", username);
            throw new UsernameNotFoundException("invalid access");
        }
    }


    public String generateToken(String username) {
        log.info("generate token for user {}", username);
        return jwtService.generateToken(username);
    }
}
