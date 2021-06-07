package it.stage.microservices.clientui.proxy;

import it.stage.microservices.clientui.bean.ProductBean;
import it.stage.microservices.clientui.payload.request.ProductRequest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 1. Only feign
/*@FeignClient(name = "product-service", url = "localhost:7001/products")
@FeignClient(name = "product-service")*/

// 2. Only feign with ribbon
/*@FeignClient(name = "product-service")
@FeignClient(name = "product-service")*/

@FeignClient(name = "api-gateway", contextId = "productContextId")
@RibbonClient(name = "product-service")
@RequestMapping("/product-service/products")
public interface ProductServiceProxy {


    @PostMapping("/insert-product")
    ProductBean insertProduct(@RequestBody ProductRequest productRequest);


    @GetMapping("/get-all-products")
    List<ProductBean> getAllProducts();

    @GetMapping("/get-product/{productId}")
    ProductBean getOneProduct(@PathVariable("productId") Long productId);

}
