/**
 * exception to catch duplicate emails since email is primary key/unique
 */
package com.beneite.SpringWithDocker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateEmailException extends RuntimeException{

    private String message;

    public DuplicateEmailException(String email) {
        super(String.format("Email: %s, already available.", email));
        this.message = message;
    }
}
