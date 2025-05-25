package br.com.techchallenge.techbites.services.execeptions;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String field, String value) {
        super("User", field, value);
    }
}
