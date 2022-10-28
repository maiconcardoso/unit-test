package br.com.maiconcardoso.unittest.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.maiconcardoso.unittest.exceptions.EntityNotFoundException;
import br.com.maiconcardoso.unittest.exceptions.EntityNotFoundExceptionsDetails;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<EntityNotFoundExceptionsDetails> entityNotFoundException(EntityNotFoundException entityNotFound) {
        EntityNotFoundExceptionsDetails details = new EntityNotFoundExceptionsDetails();
        details.setTitle("Entity Not Found");
        details.setTimestamp(Instant.now());
        details.setStatus(HttpStatus.NOT_FOUND.value());
        details.setDetails(entityNotFound.getLocalizedMessage());
        details.setDeveloperMessage(entityNotFound.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
    }
    
}
