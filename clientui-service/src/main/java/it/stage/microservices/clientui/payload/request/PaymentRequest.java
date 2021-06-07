package it.stage.microservices.clientui.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private Long orderId;

    private Double amount;

    private Long cartNumber;
}
