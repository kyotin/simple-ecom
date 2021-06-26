package com.ecommerce.product.service.product;

import com.ecommerce.product.dto.form.ProductFormDTO;
import com.ecommerce.product.dto.query.ProductQueryDTO;
import com.ecommerce.product.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.concurrent.CompletableFuture;

public interface ProductService extends BaseService<ProductQueryDTO, ProductFormDTO> {
    CompletableFuture<Page<ProductQueryDTO>> getProductOfCategory(Pageable pageable, Integer categoryId);
}
