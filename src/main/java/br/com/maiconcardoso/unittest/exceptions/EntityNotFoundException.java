package br.com.maiconcardoso.unittest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{

    public static final String MESSAGE = "Entity Not Found";
     
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
