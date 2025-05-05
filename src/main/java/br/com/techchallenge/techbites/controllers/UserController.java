package br.com.techchallenge.techbites.controllers;

import br.com.techchallenge.techbites.DTOs.UserRequestDTO;
import br.com.techchallenge.techbites.DTOs.UserResponseDTO;
import br.com.techchallenge.techbites.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UserController {

    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @PostMapping
    @Operation(summary = "Criar novo usuário", description = "Cria um novo usuário com os dados fornecidos")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(userRequest));
    }

    @GetMapping
    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(
            @RequestParam(required = false) Boolean active) {  // Esse é o parâmetro que você vai passar pela URL
        return ResponseEntity.ok(service.findAllUsers(active));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna os dados de um usuário específico")
    public ResponseEntity<Optional<UserResponseDTO>> findUserById(
            @Parameter(description = "ID do usuário a ser buscado") @PathVariable Long id) {
        return ResponseEntity.ok(service.findUserById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<UserResponseDTO> updateUser(
            @Parameter(description = "ID do usuário a ser atualizado") @PathVariable Long id,
            @RequestBody UserRequestDTO userRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUserById(id, userRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = "Desabilita um usuário do sistema")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID do usuário a ser deletado") @PathVariable Long id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/enable/{email}")
    @Operation(summary = "Habilitar usuário", description = "Habilita um usuário pelo email")
    public ResponseEntity<Void> enableUserByEmail(
            @Parameter(description = "Email do usuário a ser habilitado") @PathVariable String email) {
        service.enableUserByEmail(email);
        return ResponseEntity.noContent().build();
    }

}