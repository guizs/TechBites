package br.com.techchallenge.techbites.controllers.handlers;


import br.com.techchallenge.techbites.DTOs.ResourceNotFoundDTO;
import br.com.techchallenge.techbites.DTOs.ValidationErrorDTO;
import br.com.techchallenge.techbites.services.execeptions.ResourceNotFoundExeception;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExeceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExeceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundExeception.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundExeception (
            ResourceNotFoundExeception e,
            HttpServletRequest request) {

        var status = HttpStatus.NOT_FOUND.value();
        var method = request.getMethod();
        var path = request.getRequestURI();

        log.warn("Resource Not Found : [{} {}] - {} (status: {})", method, path, e.getMessage(), status);

        return ResponseEntity.status(status).body(new ResourceNotFoundDTO(e.getMessage() , status , method , path));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerMethodArgumentNotValidException (
            MethodArgumentNotValidException e,
            HttpServletRequest request) {

        var status = HttpStatus.BAD_REQUEST.value();
        var method = request.getMethod();
        var path = request.getRequestURI();

        List<String> errors = new ArrayList<String>();
        for (var error : e.getBindingResult().getFieldErrors()) errors.add(error.getField() + " : " + error.getDefaultMessage());

        log.warn("Validation error: [{} {}] - Invalid fields: {}", method, path, errors);

        return ResponseEntity.status(status).body(new ValidationErrorDTO(errors , status , method , path));

    }

}
