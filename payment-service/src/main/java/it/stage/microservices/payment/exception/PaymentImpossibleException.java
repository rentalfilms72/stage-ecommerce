package it.stage.microservices.payment.exception;

public class PaymentImpossibleException extends Exception {
    public PaymentImpossibleException(String message) {
        super(message);
    }
}
