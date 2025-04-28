package br.com.techchallenge.techbites.DTOs;

import br.com.techchallenge.techbites.entities.enums.Role;

import java.time.LocalDateTime;

public record UserResponseDTO(Long id, String name, String email, Role role, LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
}