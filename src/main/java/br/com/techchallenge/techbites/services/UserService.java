package br.com.techchallenge.techbites.services;

import br.com.techchallenge.techbites.DTOs.UserRequestDTO;
import br.com.techchallenge.techbites.DTOs.UserResponseDTO;
import br.com.techchallenge.techbites.entities.User;
import br.com.techchallenge.techbites.mappers.UserMapper;
import br.com.techchallenge.techbites.repositories.UserRepository;
import br.com.techchallenge.techbites.services.execeptions.ResourceNotFoundExeception;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.repository = userRepository;
        this.mapper = userMapper;
    }

    public UserResponseDTO createUser(UserRequestDTO user) {
        User entity = this.mapper.toEntity(user);
        return this.mapper.toDTO(repository.save(entity));
    }

    public List<UserResponseDTO> findAllUsers() {
        return this.mapper.toListDTO(repository.findAll());
    }

    public Optional<UserResponseDTO> findUserById(Long id) {
        return Optional.of(repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundExeception("User with id: " + id + " Not Found")))
                .map(this.mapper::toDTO);
    }

    public UserResponseDTO updateUserById(Long id, UserRequestDTO newData) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeception("User with id: " + id + " Not Found"));

        this.mapper.updateEntity(existingUser, newData);

        return this.mapper.toDTO(this.repository.save(existingUser));
    }

    public void deleteUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeception("User with id: " + id + " Not Found"));
        repository.delete(user);
    }

}
