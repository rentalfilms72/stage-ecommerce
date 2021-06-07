package it.stage.microservices.product.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseCustom {

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Europe/Rome")
    private Instant timestamp;

    private int httpStatusCode;

    private HttpStatus httpStatus;

    private String error;

    private Object message;

    private String path;
}
