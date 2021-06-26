package com.ecommerce.product.dto.form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@SuperBuilder
public class ProductFormDTO extends BaseFormDTO {
    private Integer id;
    private String name;
    private String description;
    private String thumbnail;
    private Double price;
    private int quantity;
}
