package it.stage.microservices.clientui.proxy;

import it.stage.microservices.clientui.bean.ProductBean;
import it.stage.microservices.clientui.payload.request.ProductRequest;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@FeignClient(name = "PRODUCT-SERVICE", url = "localhost:7001/products") 1
//@FeignClient(name = "PRODUCT-SERVICE")2
@FeignClient(name = "API-GATEWAY", contextId = "productContextId")
@RibbonClient(name = "PRODUCT-SERVICE")
@RequestMapping("/product-service/products")
public interface ProductServiceProxy {


    @PostMapping("/insert-product")
    ProductBean insertProduct(@RequestBody ProductRequest productRequest);


    @GetMapping("/get-all-products")
    List<ProductBean> getAllProducts();

    @GetMapping("/get-product/{productId}")
    ProductBean getOneProduct(@PathVariable("productId") Long productId);

}
