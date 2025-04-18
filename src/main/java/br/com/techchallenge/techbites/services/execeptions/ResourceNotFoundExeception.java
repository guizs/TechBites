package br.com.techchallenge.techbites.services.execeptions;

public class ResourceNotFoundExeception extends RuntimeException {

    public ResourceNotFoundExeception(String message) {
        super(message);
    }
}
