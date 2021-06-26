package com.ecommerce.order.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = -4480719785540546084L;

    private HttpStatus status;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
