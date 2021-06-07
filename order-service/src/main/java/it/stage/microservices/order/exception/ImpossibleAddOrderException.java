package it.stage.microservices.order.exception;

public class ImpossibleAddOrderException extends Exception {
    public ImpossibleAddOrderException(String message) {
        super(message);
    }
}
