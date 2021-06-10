package it.stage.microservices.clientui.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*@ResponseStatus(HttpStatus.NOT_FOUND)*/
public class EmptyProductListException extends Exception {
    public EmptyProductListException(String message) {
        super(message);
    }
}
