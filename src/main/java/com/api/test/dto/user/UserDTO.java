package com.api.test.dto.user;

import com.api.test.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(String name, String login, String senha,String role) {
    public UserDTO(User user) {
        this(user.getName(), user.getLogin(), user.getSenha(), user.getRole().name());
    }
}
