package it.stage.microservices.clientui.proxy;

import it.stage.microservices.clientui.bean.OrderBean;
import it.stage.microservices.clientui.bean.ProductBean;
import it.stage.microservices.clientui.payload.request.OrderRequest;
import it.stage.microservices.clientui.payload.request.ProductRequest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. Only feign
/*@FeignClient(name = "order-service", url = "localhost:7002/")
@RequestMapping("/order-service/orders")*/

// 2. Only feign with ribbon
/*@FeignClient(name = "order-service")
@RibbonClient(name = "order-service")
@RequestMapping("/order-service/orders")*/

@FeignClient(name = "api-gateway", contextId = "orderContextId")
@RibbonClient(name = "order-service")
@RequestMapping("/order-service/orders")
public interface OrderServiceProxy {

    @PostMapping("/insert-order")
    OrderBean insertOrder(@RequestBody OrderRequest orderRequest);
}
