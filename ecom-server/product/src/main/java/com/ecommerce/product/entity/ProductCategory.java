package com.ecommerce.product.entity;

import com.ecommerce.product.constants.EntityConstant;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = EntityConstant.PRODUCT_CATEGORY)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ProductCategory implements Serializable {

    @EmbeddedId
    private ProductCategoryId id;

    @Embeddable
    @Data
    static class ProductCategoryId implements Serializable {
        @Column(name = "product_id")
        private Integer productId;

        @Column(name = "category_id")
        private Integer categoryId;
    }
}
