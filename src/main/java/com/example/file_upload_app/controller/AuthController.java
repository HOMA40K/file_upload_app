package com.example.file_upload_app.controller;

import com.example.file_upload_app.dto.AuthenticationResponse;
import com.example.file_upload_app.security.JwtUtil;
import com.example.file_upload_app.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        boolean isValidUser = userDetailsService.validateUser(username, password);

        if (isValidUser) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);


            String jwtToken = jwtUtil.generateToken(userDetails);


            return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}
