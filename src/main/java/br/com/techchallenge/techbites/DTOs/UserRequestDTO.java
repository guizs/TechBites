package br.com.techchallenge.techbites.DTOs;

import br.com.techchallenge.techbites.entities.enums.Role;

public record UserRequestDTO(String name, String email, String password, Role role) {
}
