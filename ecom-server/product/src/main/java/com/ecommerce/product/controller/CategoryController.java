package com.ecommerce.product.controller;

import com.ecommerce.product.constants.APIMappingConstant;
import com.ecommerce.product.dto.query.CategoryQueryDTO;
import com.ecommerce.product.service.category.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping(APIMappingConstant.CATEGORY)
public class CategoryController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<Iterable<CategoryQueryDTO>>> getAll() {
        return categoryService.getAllCategories()
                .thenApply(ResponseEntity::ok)
                .exceptionally(throwable -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
