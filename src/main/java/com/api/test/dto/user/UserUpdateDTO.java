package com.api.test.dto.user;

import com.api.test.services.validation.UserUpdateValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UserUpdateValid
public record UserUpdateDTO(

        @Size(min = 5, max = 60, message = "Deve ter entre 5 e 60 caracteres")
        @NotBlank(message = "Campo Requirido")
        String name,
        @Email(message = "Insira um email valido")
        String login,

        @NotBlank
        @Size(min = 6, max = 60, message = "Deve ter entre 6 e 60 caracteres")
        String senha,
        @NotBlank
        String role) {
}
