package it.stage.microservices.clientui.controller;

import it.stage.microservices.clientui.bean.OrderBean;
import it.stage.microservices.clientui.bean.PaymentBean;
import it.stage.microservices.clientui.bean.ProductBean;
import it.stage.microservices.clientui.payload.request.OrderRequest;
import it.stage.microservices.clientui.payload.request.PaymentRequest;
import it.stage.microservices.clientui.proxy.OrderServiceProxy;
import it.stage.microservices.clientui.proxy.PaymentServiceProxy;
import it.stage.microservices.clientui.proxy.ProductServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class ClientController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductServiceProxy productServiceProxy;

    @Autowired
    private PaymentServiceProxy paymentServiceProxy;

    @Autowired
    private OrderServiceProxy orderServiceProxy;

    // etc
    private static final List<String> CUSTOMER_EMAIL_LIST = List.of(
            "matteo@appmsa.it",
            "rossi@appmsa.it",
            "antonio@appmsa.it",
            "alberto@appmsa.it",
            "filippo@appmsa.it",
            "smith@appmsa.it",
            "anna@appmsa.it",
            "renzi@appmsa.it",
            "francois@appmsa.it",
            "romeo@appmsa.it",
            "carmen@appmsa.it",
            "olivia@appmsa.it",
            "stefania@appmsa.it",
            "melissa@appmsa.it"
    );



    /*
     * Step (1)
     * This operation just retrieve all the product and show it in a home page
     * All the products are retrieve with ProductServiceProxy
     * This method return the name of the home page Home.html
     * */
    @RequestMapping("/")
    public String home(Model model) {

        List<ProductBean> productBeanList = productServiceProxy.getAllProducts();

        model.addAttribute("productList", productBeanList);

        LOGGER.info("********* Product list empty");

        return "Home";
    }



    /*
     * Step (2)
     * THis operation just get the details of a given product
     * Return the ProductDetails.html file
     * */
    @RequestMapping("/product-details/{id}")
    public String ficheProduct(@PathVariable Long id, Model model){

        ProductBean productBean = productServiceProxy.getOneProduct(id);

        // here we are suppose to retrieve the email of the customer who will order the product
        String customerEmail = getCustomerEmailFromEmailList();

        model.addAttribute("product", productBean);
        model.addAttribute("customerEmail", customerEmail);

        return "ProductDetails";
    }


    /*
     * Step (3) and (4)
     * this method call the microservice order to place an order and get the details of the order created
     * */
    @RequestMapping(value = "/order-product/{customerEmail}/{productId}/{amount}")
    public String orderProduct(
            @PathVariable("customerEmail") String customerEmail,
            @PathVariable("productId") Long productId,
            @PathVariable("amount") Double amount,
            Model model){


        OrderRequest orderRequest = new OrderRequest();

        orderRequest.setCustomerEmail(customerEmail);
        orderRequest.setProductId(productId);
        orderRequest.setQuantity(1);

        // Call the order microservice
        OrderBean orderInserted = orderServiceProxy.insertOrder(orderRequest);

        // Show the order information and
        // the amount of the order who will be use to call the Payment microservice
        model.addAttribute("order", orderInserted);
        model.addAttribute("amount", amount);

        return "Payment";
    }




    /*
     * Step (5)
     * This method just call the PAYMENT MICROSERVICE in order to process the payment
     * */
    @RequestMapping(value = "/pay-order/{orderId}/{orderAmount}")
    public String payOrder(@PathVariable("orderId")Long orderId,
                           @PathVariable("orderAmount") Double orderAmount,
                           Model model){

        PaymentRequest paymentRequest = new PaymentRequest();

        paymentRequest.setOrderId(orderId);
        paymentRequest.setAmount(orderAmount);
        paymentRequest.setCartNumber(generateCartNumber()); // generate the cart number

        // We call the microservice payment and get the result which is a ResponseEntity<PaymentBean>
        // so that we can control the returned value
        ResponseEntity<PaymentBean> paymentBeanResp = paymentServiceProxy.payOneOrder(paymentRequest);

        //if the code is different than 201 CREATED, means that payment fail.
        boolean paymentAccepted = paymentBeanResp.getStatusCode() == HttpStatus.CREATED;

        model.addAttribute("paymentOk", paymentAccepted);

        return "Confirmation";
    }



    //Generate a random string of 16 digits to simulate a card number
    private Long generateCartNumber() {

        return ThreadLocalRandom.current().nextLong(1000_0000_0000_0000L,9000_0000_0000_0000L );
    }

    // Get one customer email
    private String getCustomerEmailFromEmailList() {

        return CUSTOMER_EMAIL_LIST.get(
                ThreadLocalRandom.current().nextInt(
                        0,
                        CUSTOMER_EMAIL_LIST.size()
                )
        );
    }

}
