package com.ecommerce.product.dto.form;

import com.ecommerce.product.dto.query.CategoryQueryDTO;
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
    private CategoryQueryDTO category;

    public boolean isValid() {
        // placeholder for checking fields later
        return id != null && price != null && quantity > 0;
    }
}
