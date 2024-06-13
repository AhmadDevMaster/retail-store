package com.retail.store.auth.controller;

import com.retail.store.auth.dto.AuthResponse;
import com.retail.store.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.retail.store.auth.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;


	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticate(@RequestBody UserDTO userDTO) {
		log.info("Authenticating user {}", userDTO.getUsername());
		try {
			String token = authService.authenticate(userDTO.getUsername(), userDTO.getPassword());
			AuthResponse authResponse = new AuthResponse(token);
			return new ResponseEntity<>(authResponse, HttpStatus.OK);
		}catch (UsernameNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
