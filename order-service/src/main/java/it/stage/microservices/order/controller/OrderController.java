package it.stage.microservices.order.controller;

import it.stage.microservices.order.entity.Order;
import it.stage.microservices.order.exception.ImpossibleAddOrderException;
import it.stage.microservices.order.exception.OrderNotFoundException;
import it.stage.microservices.order.payload.request.OrderRequest;
import it.stage.microservices.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/insert-order")
    public ResponseEntity<Order> insertOder(@RequestBody OrderRequest orderRequest) throws ImpossibleAddOrderException {

        Order newOrder = orderService.insertOrder(orderRequest);

        return new ResponseEntity<Order>(newOrder, HttpStatus.CREATED);
    }


    @GetMapping(value = "/get-order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Long orderId) throws OrderNotFoundException {

        Order order = orderService.getOrderById(orderId);

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }


    @PutMapping(value = "/update-order-payment-status/{orderId}")
    public void updateOrderPaymentStatus(@PathVariable("orderId") Long orderId) throws OrderNotFoundException {

        orderService.updateOrderPaymentStatus(orderId);
    }
}
