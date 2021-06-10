package it.stage.microservices.product.controller;

import it.stage.microservices.product.entity.Product;
import it.stage.microservices.product.exception.EmptyProductListException;
import it.stage.microservices.product.exception.ExceptionHandling;
import it.stage.microservices.product.exception.InsertProductImpossibleException;
import it.stage.microservices.product.exception.ProductNotFoundException;
import it.stage.microservices.product.payload.request.ProductListRequest;
import it.stage.microservices.product.payload.request.ProductRequest;
import it.stage.microservices.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends ExceptionHandling {


    @Autowired
    ProductService productService;


    @PostMapping(value = "/insert-product", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product insertProduct(@RequestBody ProductRequest productRequest)
            throws InsertProductImpossibleException {

        return productService.insertProduct(productRequest);
    }

    @PostMapping(value = "/insert-many-products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> insertManyProduct(@RequestBody ProductListRequest productListRequest)
            throws InsertProductImpossibleException {

        return productService.insertManyProducts(productListRequest);
    }

    @GetMapping(value = "/get-all-products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() throws EmptyProductListException {

        return productService.getAllProducts();
    }

    @GetMapping(value = "/get-product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getOneProduct(@PathVariable("productId") Long productId)
            throws ProductNotFoundException {

        return productService.getProductById(productId);
    }

}
