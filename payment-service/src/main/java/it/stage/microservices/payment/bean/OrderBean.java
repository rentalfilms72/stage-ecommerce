package it.stage.microservices.payment.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBean {

    private Long orderId;

    private Long productId;

    private String customerEmail;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Rome")
    private Date dateOrder;

    private Integer quantity;

    private boolean orderPay;
}
