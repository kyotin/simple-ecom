package com.ecommerce.product.entity;

import com.ecommerce.product.constants.EntityConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = EntityConstant.PRODUCT_CATEGORY)
public class ProductCategory {

    @EmbeddedId
    private ProductCategoryId id;

    @Embeddable
    @EqualsAndHashCode
    static class ProductCategoryId implements Serializable {
        @Column(name = "product_id")
        private Integer productId;

        @Column(name = "category_id")
        private Integer categoryId;

        public ProductCategoryId() {

        }

        public ProductCategoryId(Integer productId, Integer categoryId) {
            this.productId = productId;
            this.categoryId = categoryId;
        }
    }
}
