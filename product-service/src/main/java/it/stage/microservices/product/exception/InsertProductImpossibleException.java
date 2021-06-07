package it.stage.microservices.product.exception;

public class InsertProductImpossibleException extends Exception {
    public InsertProductImpossibleException(String message) {
        super(message);
    }
}
