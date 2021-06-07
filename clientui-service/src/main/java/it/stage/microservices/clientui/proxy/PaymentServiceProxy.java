package it.stage.microservices.clientui.proxy;

import it.stage.microservices.clientui.bean.PaymentBean;
import it.stage.microservices.clientui.bean.ProductBean;
import it.stage.microservices.clientui.payload.request.PaymentRequest;
import it.stage.microservices.clientui.payload.request.ProductRequest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*@FeignClient(name = "PAYMENT-SERVICE", url = "localhost:7003/payments")
@RequestMapping("/payment-service/payments")*/

@FeignClient(name = "API-GATEWAY", contextId = "paymentContextId")
@RibbonClient(name = "PAYMENT-SERVICE")
@RequestMapping("/payment-service/payments")
public interface PaymentServiceProxy {


    @PostMapping("/insert-payment")
    ResponseEntity<PaymentBean> payOneOrder(@RequestBody PaymentRequest paymentRequest);
}
