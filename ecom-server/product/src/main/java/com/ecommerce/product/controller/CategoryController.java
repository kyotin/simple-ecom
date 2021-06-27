package com.ecommerce.product.controller;

import com.ecommerce.product.constants.APIMappingConstant;
import com.ecommerce.product.dto.ResponseDTO;
import com.ecommerce.product.dto.query.CategoryQueryDTO;
import com.ecommerce.product.service.category.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<ResponseDTO<Iterable<CategoryQueryDTO>>> getAll() {
        return categoryService.getAllCategories()
                .thenApply(v -> ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(), "Success", v)))
                .exceptionally(throwable -> ResponseEntity.internalServerError().body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), throwable.getMessage())))
                .join();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<CategoryQueryDTO>> getCategory(@PathVariable Integer id) {
        return categoryService.getCategory(id)
                .thenApply(v -> ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(), "Success", v)))
                .exceptionally(throwable -> ResponseEntity.internalServerError().body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), throwable.getMessage())))
                .join();
    }
}
