package it.stage.microservices.clientui.proxy;

import it.stage.microservices.clientui.bean.OrderBean;
import it.stage.microservices.clientui.bean.ProductBean;
import it.stage.microservices.clientui.payload.request.OrderRequest;
import it.stage.microservices.clientui.payload.request.ProductRequest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*@FeignClient(name = "ORDER-SERVICE", url = "localhost:7002/orders")
@RequestMapping("/order-service/orders")*/

@FeignClient(name = "API-GATEWAY", contextId = "orderContextId")
@RibbonClient(name = "ORDER-SERVICE")
@RequestMapping("/order-service/orders")
public interface OrderServiceProxy {

    @PostMapping("/insert-order")
    OrderBean insertOrder(@RequestBody OrderRequest orderRequest);
}
