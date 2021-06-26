package com.ecommerce.product.entity;

import com.ecommerce.product.constants.EntityConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(name = EntityConstant.PRODUCT_IMG)
public class ProductImg {

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
