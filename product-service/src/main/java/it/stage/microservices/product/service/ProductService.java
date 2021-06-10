package it.stage.microservices.product.service;

import it.stage.microservices.product.config.ApplicationPropertiesConfig;
import it.stage.microservices.product.entity.Product;
import it.stage.microservices.product.exception.EmptyProductListException;
import it.stage.microservices.product.exception.InsertProductImpossibleException;
import it.stage.microservices.product.exception.ProductNotFoundException;
import it.stage.microservices.product.payload.request.ProductListRequest;
import it.stage.microservices.product.payload.request.ProductRequest;
import it.stage.microservices.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ApplicationPropertiesConfig applicationPropertiesConfig;



    public Product insertProduct(ProductRequest productRequest) throws InsertProductImpossibleException {

        Product newProduct = new Product();
        newProduct.setTitle(productRequest.getTitle());
        newProduct.setDescription(productRequest.getDescription());
        newProduct.setImage(productRequest.getImage());
        newProduct.setPrice(productRequest.getPrice());

        newProduct = productRepository.save(newProduct);

        if(newProduct == null)
            throw new InsertProductImpossibleException("Error, impossible to create this product, try later");

        return newProduct;
    }

    public List<Product> insertManyProducts(ProductListRequest productListRequest)
            throws InsertProductImpossibleException {

        List<Product> productList = new ArrayList<>();

        for (ProductRequest productRequest: productListRequest.getProductListRequest()) {
            productList.add(insertProduct(productRequest));
        }

        return productList;
    }


    public List<Product> getAllProducts() throws  EmptyProductListException {

        List<Product> products = productRepository.findAll();

        if(products.isEmpty()) {
            log.info("**** PRODUCT LIST EMPTY .........");
            throw new EmptyProductListException("No product available for sale");
        }

        int numberOfProduct = products.size();

        return products.subList(0, applicationPropertiesConfig.getLimitOfProducts());
    }



    public Product getProductById(Long productId) throws ProductNotFoundException {

        Optional<Product> product = productRepository.findById(productId);

        if(product.isEmpty())
            throw new ProductNotFoundException("Product with id " + productId + " not exist");

        return product.get();
    }
}
