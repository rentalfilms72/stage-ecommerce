package it.stage.microservices.product.exception;

/*@ResponseStatus(HttpStatus.NOT_FOUND)*/
public class EmptyProductListException extends Exception {
    public EmptyProductListException(String message) {
        super(message);
    }
}
