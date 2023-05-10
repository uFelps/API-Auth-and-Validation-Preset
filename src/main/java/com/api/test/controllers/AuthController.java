package com.api.test.controllers;

import com.api.test.dto.token.TokenDTO;
import com.api.test.dto.user.UserLoginDTO;
import com.api.test.dto.user.UserSignupDTO;
import com.api.test.dto.user.UserUpdateDTO;
import com.api.test.entities.User;
import com.api.test.services.auth.AuthService;
import com.api.test.services.auth.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserLoginDTO dto){

        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/signup")
    public ResponseEntity<TokenDTO> signUp(@RequestBody @Valid UserSignupDTO dto){

        return ResponseEntity.ok(authService.signUp(dto));
    }


}
