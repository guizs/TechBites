package br.com.techchallenge.techbites.DTOs;

import br.com.techchallenge.techbites.entities.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

public record UserRequestDTO(

        @NotBlank(message = "Name is required.")
        String name,

        @Email(message = "Email format is invalid.")
        @NotBlank(message = "Email is required.")
        String email,

        @Size(min = 6, message = "Password must be at least 6 characters.")
        @NotBlank(message = "Password is required.")
        String password,

        @NotNull(message = "Invalid role. Allowed values: [ADMIN, USER, USER_RESTAURANT ].")
        Role role

) {
}
