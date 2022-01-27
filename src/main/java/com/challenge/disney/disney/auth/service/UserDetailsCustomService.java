package com.challenge.disney.disney.auth.service;

import com.challenge.disney.disney.auth.dto.UserDTO;
import com.challenge.disney.disney.auth.entity.UserEntity;
import com.challenge.disney.disney.auth.repository.UserRepository;
import com.challenge.disney.disney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private EmailService emailService;

    public UserDetailsCustomService(@Autowired @Lazy PasswordEncoder passwordEncoder, @Autowired @Lazy UserRepository userRepository, @Autowired @Lazy EmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null){
            throw new UsernameNotFoundException("Username or password not fount");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(UserDTO userDTO){

        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        //userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity = this.userRepository.save(userEntity);
        if (userEntity != null){
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }

}
