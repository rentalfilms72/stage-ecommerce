package it.stage.microservices.payment.exception;

public class PaymentExistException extends Exception {
    public PaymentExistException(String message) {
        super(message);
    }
}
