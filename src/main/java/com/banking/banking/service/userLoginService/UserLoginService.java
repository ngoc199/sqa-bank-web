package com.banking.banking.service.userLoginService;

import com.banking.banking.model.user.UserLogin;
import com.banking.banking.repository.userLoginRepo.UserLoginRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginService.class);

    private final UserLoginRepository userLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin userLogin = userLoginRepository.findByUsername(username).orElseThrow(() -> {
            LOGGER.error("Login error");
            return new UsernameNotFoundException("username not found");});
        return userLogin;
    }

}
