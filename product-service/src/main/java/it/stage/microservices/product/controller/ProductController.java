package it.stage.microservices.product.controller;

import it.stage.microservices.product.entity.Product;
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


    @PostMapping("/insert-product")
    public ResponseEntity<Product> insertProduct(@RequestBody ProductRequest productRequest)
            throws InsertProductImpossibleException {

        Product newProduct = productService.insertProduct(productRequest);

        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PostMapping("/insert-many-products")
    public ResponseEntity<List<Product>> insertManyProduct(@RequestBody ProductListRequest productListRequest)
            throws InsertProductImpossibleException {

        List<Product> newProductList = productService.insertManyProducts(productListRequest);

        return new ResponseEntity<>(newProductList, HttpStatus.CREATED);
    }


    /*@GetMapping(value = "/get-all-products", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<List<Product>> getAllProducts() throws ProductNotFoundException {

        List<Product> productList =  productService.getAllProducts();

        return new ResponseEntity<>(productList, HttpStatus.CREATED);
    }*/

    @GetMapping(value = "/get-all-products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() throws ProductNotFoundException {

        return productService.getAllProducts();
    }

    @GetMapping("/get-product/{productId}")
    public ResponseEntity<Product> getOneProduct(@PathVariable("productId") Long productId)
            throws ProductNotFoundException {

        Product product = productService.getProductById(productId);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

}
