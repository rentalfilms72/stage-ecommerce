package it.stage.microservices.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDERS")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderId;

    private Long productId;

    private String customerEmail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATE_ORDER", updatable=false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Rome")
    private Date dateOrder;

    private Integer quantity;

    private boolean orderPay;

    @Override
    public String toString() {
        return "Order{" +
                " orderId=" + orderId +
                " productId=" + productId +
                ", dateOrder=" + dateOrder +
                ", quantity=" + quantity +
                ", orderPay=" + orderPay +
                '}';
    }
}