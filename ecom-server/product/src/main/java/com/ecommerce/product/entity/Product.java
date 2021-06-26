package com.ecommerce.product.entity;

import com.ecommerce.product.constants.EntityConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = EntityConstant.PRODUCT)
public class Product extends BaseEntity {

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "thumbnail", length = 500)
    private String thumbnail;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private int quantity;
}
