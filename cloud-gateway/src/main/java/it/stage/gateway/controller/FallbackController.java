package it.stage.gateway.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController
public class FallbackController {

    Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/productFallBack")
    public String productServiceFallBack() {
        log.info("Product Service is taking too long to respond or is down. Please try again later");
        return "Product Service is taking too long to respond or is down. Please try again later";
    }

    @RequestMapping("/orderFallBack")
    public String orderServiceFallBack() {
        log.info("Order Service is taking too long to respond or is down. Please try again later");
        return "Order Service is taking too long to respond or is down. Please try again later";
    }

    @RequestMapping("/paymentFallback")
    public String paymentServiceFallBack() {
        log.info("Payment Service is taking too long to respond or is down. Please try again later");
        return "Payment Service is taking too long to respond or is down. Please try again later";
    }
}
