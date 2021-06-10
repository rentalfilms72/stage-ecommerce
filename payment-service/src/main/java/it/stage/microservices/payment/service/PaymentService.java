package it.stage.microservices.payment.service;

import it.stage.microservices.payment.entity.Payment;
import it.stage.microservices.payment.exception.PaymentExistException;
import it.stage.microservices.payment.exception.PaymentImpossibleException;
import it.stage.microservices.payment.payload.request.PaymentRequest;
import it.stage.microservices.payment.proxy.OrderServiceProxy;
import it.stage.microservices.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderServiceProxy orderServiceProxy;



    public Payment payOneOrder(PaymentRequest paymentRequest)
            throws PaymentExistException, PaymentImpossibleException {

        Optional<Payment> paymentExist =
                paymentRepository.findPaymentByOrderId(paymentRequest.getOrderId());

        if(paymentExist.isPresent())
            throw new PaymentExistException("This order is already pay");

        Payment newPayment = new Payment();
        newPayment.setCartNumber(paymentRequest.getCartNumber());
        newPayment.setAmount(paymentRequest.getAmount());
        newPayment.setOrderId(paymentRequest.getOrderId());

        newPayment = paymentRepository.save(newPayment);

        if(newPayment == null)
            throw new PaymentImpossibleException("Error, impossible to make payment, try later");


        // Call the order microservices to changes the status of payment
        // notifier the mail microservice with kafka to send the mail to the customer
        orderServiceProxy.updateOrderPaymentStatus(newPayment.getOrderId());

        return newPayment;
    }
}
