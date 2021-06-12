package it.stage.microservices.clientui.proxy;

import it.stage.microservices.clientui.bean.OrderBean;
import it.stage.microservices.clientui.bean.ProductBean;
import it.stage.microservices.clientui.payload.request.OrderRequest;
import it.stage.microservices.clientui.payload.request.ProductRequest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. Only feign
/*@FeignClient(name = "order-service", url = "localhost:7002/")
@RequestMapping("/order-service/orders")*/

// 2. Only feign with ribbon
/*@FeignClient(name = "order-service")
@RibbonClient(name = "order-service")
@RequestMapping("/order-service/orders")*/

@FeignClient(name = "cloud-gateway",
        contextId = "orderContextId",
        url = "${GATEWAY_HOSTNAME_AND_PORT:localhost:6003}")
@RibbonClient(name = "order-service")
@RequestMapping("/orders")
public interface OrderServiceProxy {

    @PostMapping(value = "/insert-order", consumes = MediaType.APPLICATION_JSON_VALUE)
    OrderBean insertOrder(@RequestBody OrderRequest orderRequest);
}
