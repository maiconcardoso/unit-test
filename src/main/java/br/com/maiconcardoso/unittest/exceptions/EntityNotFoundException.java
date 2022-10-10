package br.com.maiconcardoso.unittest.exceptions;

public class EntityNotFoundException extends RuntimeException{

    private static final String MESSAGE = "Entity Not Found";
     
    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
