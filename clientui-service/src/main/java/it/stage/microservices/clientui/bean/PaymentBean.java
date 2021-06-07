package it.stage.microservices.clientui.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentBean {

    private Long paymentId;

    private Long orderId;

    private Double amount;

    private Long cartNumber;
}
