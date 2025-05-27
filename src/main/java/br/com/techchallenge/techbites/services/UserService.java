package br.com.techchallenge.techbites.services;

import br.com.techchallenge.techbites.DTOs.UserRequestDTO;
import br.com.techchallenge.techbites.DTOs.UserResponseDTO;
import br.com.techchallenge.techbites.entities.User;
import br.com.techchallenge.techbites.mappers.UserMapper;
import br.com.techchallenge.techbites.repositories.UserRepository;
import br.com.techchallenge.techbites.services.exceptions.DuplicateKeyException;
import br.com.techchallenge.techbites.services.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

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

    public UserResponseDTO createUser(UserRequestDTO userDto) {

        this.validateEmail(userDto.email());

        User entity = this.mapper.toEntity(userDto);
        entity.setActive(true);
        return this.mapper.toDTO(repository.save(entity));
    }

    public List<UserResponseDTO> findAllUsers(Boolean active) {
        List<User> users;

        if (active == null) {
            users = repository.findAll();
        } else {
            users = repository.findByActive(active);
        }

        return this.mapper.toListDTO(users);
    }

    public Optional<UserResponseDTO> findUserById(Long id) {
        return Optional.of(repository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("id" , id.toString())))
                .map(this.mapper::toDTO);
    }

    public UserResponseDTO updateUserById(Long id, UserRequestDTO newData) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id" , id.toString()));

        Optional<User> userByEmail = repository.findByEmail(newData.email());

        if (userByEmail.isPresent() && !userByEmail.get().getId().equals(id)) {
             throw new DuplicateKeyException("User" , "email" , newData.email());
        }

        this.mapper.updateEntity(existingUser, newData);

        return this.mapper.toDTO(this.repository.save(existingUser));
    }

    public void deleteUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("id" , id.toString()));
        repository.delete(user);
    }

    public void enableUserByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("email" , email));
        user.setActive(true);
        repository.save(user);
    }

    private void validateEmail(String email) {
        if (repository.findByEmail(email).isPresent()) {
            throw new DuplicateKeyException("User" , "email" , email);
        }
    }


}
