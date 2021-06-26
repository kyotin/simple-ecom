package com.ecommerce.product.service.category;

import com.ecommerce.product.dto.query.CategoryQueryDTO;

import java.util.concurrent.CompletableFuture;

public interface CategoryService {
    CompletableFuture<Iterable<CategoryQueryDTO>> getAllCategories();
}
