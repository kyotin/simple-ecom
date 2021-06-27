package com.ecommerce.order.dto;

import com.ecommerce.order.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDTO implements Serializable {
    private OrderStatus status;
    private Double totalPrice;
    private BuyerDTO buyer;
    private Set<OrderProductDTO> products;

    public void calculatePrice() {
        this.products.forEach(p -> {
            if (this.totalPrice == null) {
                this.totalPrice = 0.0;
            }
            this.totalPrice += p.getPrice();
        });
    }

    public Boolean isValid() {
        if (this.products.size() > 0) {
            for (OrderProductDTO product : this.products) {
               if (!product.isValid()) {
                   return false;
               }
            }
        }

        return buyer.isValid();
    }

}
