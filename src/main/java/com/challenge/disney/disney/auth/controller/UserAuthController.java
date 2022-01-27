package com.challenge.disney.disney.auth.controller;

import com.challenge.disney.disney.auth.dto.AuthenticationRequest;
import com.challenge.disney.disney.auth.dto.AuthenticationResponse;
import com.challenge.disney.disney.auth.dto.UserDTO;
import com.challenge.disney.disney.auth.service.JwtUtils;
import com.challenge.disney.disney.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private UserDetailsCustomService userDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtTokenUtil;

    public UserAuthController(@Autowired @Lazy UserDetailsCustomService userDetailsService, AuthenticationManager authenticationManager, JwtUtils jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUp(@Valid @RequestBody UserDTO user) throws Exception {
        this.userDetailsService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest authReq) throws Exception {

        UserDetails userDetails;
        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authReq.getUsername(),
                            authReq.getPassword()
                    )
            );

            userDetails = (UserDetails) auth.getPrincipal();

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
