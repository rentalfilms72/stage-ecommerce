package it.stage.microservices.clientui.exception;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    // Create the instance of a default decoder of feign
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {


        if(response.status() == 500 ) {
            return new ProductBadRequestException("Bad request.");
        }
        else if (response.status() == 404 ) {
            return new ProductNotFoundException("Product not found.");
        }
        else if (response.status() == 401 ) { //Unauthorized
            return new FeignException.Unauthorized("Product not found.", null, null);
        }


        // Otherwise we past the error to the default decoder
        return defaultErrorDecoder.decode(s, response);
    }
}
