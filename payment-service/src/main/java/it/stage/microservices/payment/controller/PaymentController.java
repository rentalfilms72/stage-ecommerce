package it.stage.microservices.payment.controller;


import it.stage.microservices.payment.entity.Payment;
import it.stage.microservices.payment.exception.PaymentExistException;
import it.stage.microservices.payment.exception.PaymentImpossibleException;
import it.stage.microservices.payment.payload.request.PaymentRequest;
import it.stage.microservices.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping(value = "/insert-payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public Payment payOneOrder(@RequestBody PaymentRequest paymentRequest)
            throws PaymentExistException, PaymentImpossibleException {

        return paymentService.payOneOrder(paymentRequest);
    }

}
