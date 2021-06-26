package com.ecommerce.product.mapper;

import com.ecommerce.product.dto.form.ProductFormDTO;
import com.ecommerce.product.dto.query.ProductQueryDTO;
import com.ecommerce.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductQueryDTO modelToQueryDto(Product model);

    Product queryDtoToModel(ProductQueryDTO dto);

    ProductFormDTO modelToFormDto(Product model);

    Product formDtoToModel(ProductFormDTO dto);

    static ProductMapper getInstance() {
        return Mappers.getMapper(ProductMapper.class);
    }

    static ProductQueryDTO convertToQueryDto(Product model) {
        return getInstance().modelToQueryDto(model);
    }

    static ProductFormDTO convertToFormDto(Product model) {
        return getInstance().modelToFormDto(model);
    }

    static Product convertToModel(ProductQueryDTO dto) {
        return getInstance().queryDtoToModel(dto);
    }

    static Product convertToModel(ProductFormDTO dto) {
        return getInstance().formDtoToModel(dto);
    }
}
