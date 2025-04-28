package br.com.techchallenge.techbites.controllers;

import br.com.techchallenge.techbites.DTOs.UserRequestDTO;
import br.com.techchallenge.techbites.DTOs.UserResponseDTO;
import br.com.techchallenge.techbites.entities.User;
import br.com.techchallenge.techbites.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setRole(userRequest.role());
        user.setCreatedAt(LocalDateTime.now());
        user.setLastUpdatedAt(LocalDateTime.now());

        User savedUser = userService.createUser(user);

        UserResponseDTO response = new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole(), savedUser.getCreatedAt(), savedUser.getLastUpdatedAt());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();

        List<UserResponseDTO> userResponses = users.stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getCreatedAt(), user.getLastUpdatedAt()))
                .toList();

        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable Long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new RuntimeException("User not found"));

        UserResponseDTO response = new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getCreatedAt(), user.getLastUpdatedAt());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequest) {
        User updatedUser = new User();
        updatedUser.setName(userRequest.name());
        updatedUser.setEmail(userRequest.email());
        updatedUser.setPassword(userRequest.password());
        updatedUser.setRole(userRequest.role());

        User savedUser = userService.updateUserById(id, updatedUser);

        UserResponseDTO response = new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole(), savedUser.getCreatedAt(), savedUser.getLastUpdatedAt());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
