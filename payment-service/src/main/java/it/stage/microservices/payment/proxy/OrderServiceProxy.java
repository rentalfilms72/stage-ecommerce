package it.stage.microservices.payment.proxy;

import it.stage.microservices.payment.bean.OrderBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/*@FeignClient(name = "order-service", url = "localhost:7002")
@RequestMapping("/orders")*/

@FeignClient(name = "order-service")
@RibbonClient(name = "order-service")
@RequestMapping("/orders")
public interface OrderServiceProxy {

    @GetMapping(value = "/get-order/{orderId}")
    OrderBean getOrderById(@PathVariable("orderId") Long orderId);

    @PutMapping(value = "/update-order-payment-status/{orderId}")
    void updateOrderPaymentStatus(@PathVariable("orderId") Long orderId);

}