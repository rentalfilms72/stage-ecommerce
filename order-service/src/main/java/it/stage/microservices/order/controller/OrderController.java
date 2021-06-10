package it.stage.microservices.order.controller;

import it.stage.microservices.order.entity.Order;
import it.stage.microservices.order.exception.ImpossibleAddOrderException;
import it.stage.microservices.order.exception.OrderNotFoundException;
import it.stage.microservices.order.payload.request.OrderRequest;
import it.stage.microservices.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping(value = "/insert-order", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order insertOder(@RequestBody OrderRequest orderRequest) throws ImpossibleAddOrderException {

        return orderService.insertOrder(orderRequest);
    }


    @GetMapping(value = "/get-order/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrderById(@PathVariable("orderId") Long orderId) throws OrderNotFoundException {

        return orderService.getOrderById(orderId);
    }


    @PutMapping(value = "/update-order-payment-status/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateOrderPaymentStatus(@PathVariable("orderId") Long orderId) throws OrderNotFoundException {

        orderService.updateOrderPaymentStatus(orderId);
    }
}
