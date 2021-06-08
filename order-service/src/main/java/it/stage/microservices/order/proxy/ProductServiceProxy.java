package it.stage.microservices.order.proxy;


import it.stage.microservices.order.bean.ProductBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*@FeignClient(name = "product-service", url = "localhost:7001")
@RequestMapping("/products")*/

/*@FeignClient(name = "product-service")
@RibbonClient(name = "product-service")
@RequestMapping("/products")*/
@FeignClient(name = "product-service", url = "localhost:7001")
public interface ProductServiceProxy {

    @GetMapping("/get-product/{productId}")
    ProductBean getOneProduct(@PathVariable("productId") Long productId);
}
