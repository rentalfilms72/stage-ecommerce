package it.stage.microservices.product.exception;

import it.stage.microservices.product.api.ApiResponseCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;


@RestControllerAdvice
public class ExceptionHandling {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());


    private ResponseEntity<ApiResponseCustom> createHttpResponse(
            HttpStatus httpStatus,
            String message) {

        return new ResponseEntity<>(new ApiResponseCustom(Instant.now(), httpStatus.value(),
                httpStatus, "", message, ""),
                httpStatus);
    }

    @ExceptionHandler(EmptyProductListException.class)
    public ResponseEntity<ApiResponseCustom> emptyProductListException(
            EmptyProductListException exception) {
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponseCustom> productNotFoundException(
            ProductNotFoundException exception) {
        return createHttpResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }
}
