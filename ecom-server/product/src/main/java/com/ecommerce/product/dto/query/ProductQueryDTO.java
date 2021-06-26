package com.ecommerce.product.dto.query;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
public class ProductQueryDTO extends BaseQueryDTO {
    private String name;
    private String description;
    private String thumbnail;
    private Double price;
    private int quantity;
}
