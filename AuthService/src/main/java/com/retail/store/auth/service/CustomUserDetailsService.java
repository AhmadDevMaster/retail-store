package com.retail.store.auth.service;

import com.retail.store.auth.model.SecurityUser;
import com.retail.store.auth.repo.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private  AuthRepo authRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		log.debug("start checking user if exist {}", username);
		return authRepo.findByUsername(username).map(SecurityUser::new).orElseThrow(() -> {
			log.debug("Invalid username  {}", username);
			return new UsernameNotFoundException("User not found");
		});
	}
}
