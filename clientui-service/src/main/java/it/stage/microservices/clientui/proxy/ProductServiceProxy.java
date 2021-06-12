package it.stage.microservices.clientui.proxy;

import it.stage.microservices.clientui.bean.ProductBean;
import it.stage.microservices.clientui.exception.EmptyProductListException;
import it.stage.microservices.clientui.payload.request.ProductRequest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. Only feign
/*@FeignClient(name = "product-service", url = "localhost:7001/products")
@FeignClient(name = "product-service")*/

// 2. Only feign with ribbon
/*@FeignClient(name = "product-service")
@FeignClient(name = "product-service")*/

@FeignClient(
        name = "cloud-gateway",
        contextId = "productContextId",
        url = "${GATEWAY_HOSTNAME_AND_PORT:localhost:6003}")
@RibbonClient(name = "product-service")
@RequestMapping("/products")
public interface ProductServiceProxy {


    @PostMapping(value = "/insert-product", consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductBean insertProduct(@RequestBody ProductRequest productRequest);

    @GetMapping(value = "/get-all-products", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<ProductBean> getAllProducts() throws EmptyProductListException;

    @GetMapping(value = "/get-product/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductBean getOneProduct(@PathVariable("productId") Long productId);

}
