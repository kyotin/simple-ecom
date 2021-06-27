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
public class BuyerDTO implements Serializable {
    private String name;
    private String phoneNumber;
    private String email;
    private String homeAddress;

    public Boolean isValid() {
        return name.length() > 0 && phoneNumber.length() > 0 &&
                email.length() > 0 && homeAddress.length() > 0;
    }
}
