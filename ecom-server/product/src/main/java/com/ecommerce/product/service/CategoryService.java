package com.ecommerce.product.service;

import com.ecommerce.product.dto.query.CategoryQueryDTO;

import java.util.concurrent.CompletableFuture;

public interface CategoryService {
    CompletableFuture<Iterable<CategoryQueryDTO>> getAllCategories();
    CompletableFuture<CategoryQueryDTO> getCategory(Integer id);
}
