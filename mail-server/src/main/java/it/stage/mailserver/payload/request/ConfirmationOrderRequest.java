package it.stage.mailserver.payload.request;

import it.stage.mailserver.bean.OrderBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationOrderRequest {

    private String emailTo;

    private Long orderId;

    private Integer quantity;


    public static ConfirmationOrderRequest createFrom(OrderBean orderBean) {

        return new ConfirmationOrderRequest(
                orderBean.getCustomerEmail(),
                orderBean.getOrderId(),
                orderBean.getQuantity());
    }
}
