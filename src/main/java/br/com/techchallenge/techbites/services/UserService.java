package br.com.techchallenge.techbites.services;

import br.com.techchallenge.techbites.entities.User;
import br.com.techchallenge.techbites.repositories.UserRepository;
import br.com.techchallenge.techbites.services.execeptions.ResourceNotFoundExeception;
import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(User user) {
        return repository.save(user);
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeception("User with id: " + id + "Not Found")));
    }

    public User updateUserById(Long id, User newData) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeception("User with id: " + id + " Not Found"));

        return updateUser(existingUser, newData);
    }

    private User updateUser(User existingUser, User newData) {
        existingUser.setName(newData.getName());
        existingUser.setEmail(newData.getEmail());
        existingUser.setPassword(newData.getPassword());
        existingUser.setRole(newData.getRole());
        existingUser.setLastUpdatedAt(LocalDateTime.now());

        return repository.save(existingUser);
    }

    public void deleteUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExeception("User with id: " + id + " Not Found"));
        repository.delete(user);
    }

}
