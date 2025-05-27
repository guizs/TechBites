package br.com.techchallenge.techbites.DTOs;

import java.time.Instant;

public record ErrorResponseDTO(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path, String method) {
}
