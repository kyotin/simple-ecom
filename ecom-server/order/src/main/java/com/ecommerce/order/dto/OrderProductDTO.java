package com.ecommerce.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDTO implements Serializable {
    private Integer productId;
    private String name;
    private Double price;
    private Integer quantity;

    /**
     * Validate this OrderProductDTO
     */
    public Boolean isValid() {
        return productId != null && productId > 0 &&
                price != null && price > 0 &&
                quantity != null && quantity > 0;

    }
}
