package com.ecommerce.order.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {
    private String code;
    private String message;
    private Boolean isError;
    private T data;
    private Long timestamp;

    public ResponseDTO(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
