package com.ecommerce.product.entity;

import com.ecommerce.product.constants.EntityConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = EntityConstant.PRODUCT_IMG)
public class ProductImg implements Serializable{

    @EmbeddedId
    private ProductImgId id;

    @Embeddable
    @EqualsAndHashCode
    static class ProductImgId implements Serializable {
        @Column(name = "product_id")
        private Integer productId;

        @Column(name = "img_id")
        private Integer imgId;

        public ProductImgId() {
        }

        public ProductImgId(Integer productId, Integer imgId) {
            this.productId = productId;
            this.imgId = imgId;
        }
    }
}
