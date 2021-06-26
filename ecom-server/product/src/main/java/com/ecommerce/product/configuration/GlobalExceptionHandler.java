package com.ecommerce.product.configuration;

import com.ecommerce.product.dto.ResponseDTO;
import com.ecommerce.product.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDTO<CustomException>> handleCustomException(CustomException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatus())
                .body(new ResponseDTO<>(ex.getStatus().getReasonPhrase(), ex.getMessage()));
    }
}
