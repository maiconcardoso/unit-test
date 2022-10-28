package br.com.maiconcardoso.unittest.exceptions;

import java.time.Instant;

import lombok.Data;

@Data
public class ExceptionsDetails {

    private String title;
    private int status;
    private String details;
    private String developerMessage;
    private Instant timestamp; 

}
