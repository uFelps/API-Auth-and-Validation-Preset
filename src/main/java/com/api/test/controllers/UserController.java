package com.api.test.controllers;

import com.api.test.dto.token.TokenDTO;
import com.api.test.dto.user.UserDTO;
import com.api.test.dto.user.UserLoginDTO;
import com.api.test.dto.user.UserSignupDTO;
import com.api.test.dto.user.UserUpdateDTO;
import com.api.test.entities.User;
import com.api.test.services.UserService;
import com.api.test.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<UserDTO> atualizar(@RequestBody @Valid UserUpdateDTO dto, @PathVariable Long id){

        return ResponseEntity.ok(userService.atualizar(id, dto));
    }

}
