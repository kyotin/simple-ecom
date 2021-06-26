package com.ecommerce.product.dto.query;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@NoArgsConstructor
public class CategoryQueryDTO extends BaseQueryDTO {
    private Integer id;
    private String name;
}
