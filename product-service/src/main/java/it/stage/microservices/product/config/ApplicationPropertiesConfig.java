package it.stage.microservices.product.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("my-config")
public class ApplicationPropertiesConfig {

    private int limitOfProducts;
}
