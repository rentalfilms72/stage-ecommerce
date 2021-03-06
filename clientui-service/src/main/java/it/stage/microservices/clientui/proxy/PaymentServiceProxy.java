package it.stage.microservices.clientui.proxy;

import it.stage.microservices.clientui.bean.PaymentBean;
import it.stage.microservices.clientui.bean.ProductBean;
import it.stage.microservices.clientui.payload.request.PaymentRequest;
import it.stage.microservices.clientui.payload.request.ProductRequest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. Only feign
/*@FeignClient(name = "payment-service", url = "localhost:7003/payments")
@RequestMapping("/payment-service/payments")*/

// 2. Only feign with ribbon
/*@FeignClient(name = "payment-service")
@RequestMapping("/payment-service/payments")*/

@FeignClient(name = "cloud-gateway",
        contextId = "paymentContextId",
        url = "${GATEWAY_HOSTNAME_AND_PORT:localhost:6003}")
@RibbonClient(name = "payment-service")
@RequestMapping("/payments")
public interface PaymentServiceProxy {


    @PostMapping(value = "/insert-payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    PaymentBean payOneOrder(@RequestBody PaymentRequest paymentRequest);
}
