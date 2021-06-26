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
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = EntityConstant.IMG)
public class Img extends BaseEntity {

    @Column(name = "link", length = 100)
    private String link;

    @Column(name = "name", length = 500)
    private String name;
}
