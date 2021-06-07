package it.stage.microservices.clientui.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductBean {

    private Long productId;

    private String title;

    private String description;

    private String image;

    private Double price;
}
