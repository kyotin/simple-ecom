package com.ecommerce.product.mapper;

import com.ecommerce.product.dto.form.ProductFormDTO;
import com.ecommerce.product.dto.query.CategoryQueryDTO;
import com.ecommerce.product.dto.query.ProductQueryDTO;
import com.ecommerce.product.entity.Category;
import com.ecommerce.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Category buildCategory(CategoryQueryDTO dto);

    CategoryQueryDTO buildCategoryQuery(Category category);

    ProductQueryDTO buildProductQuery(Product model);

    Product buildProduct(ProductQueryDTO dto);

    ProductFormDTO buildProductForm(Product model);

    Product buildProduct(ProductFormDTO dto);

}
