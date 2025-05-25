package br.com.techchallenge.techbites.DTOs;

public record DuplicateKeyDTO(String message, int status, String method, String path) {
}
