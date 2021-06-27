package com.ecommerce.product.service.impl;

import com.ecommerce.product.constants.ErrorConstant;
import com.ecommerce.product.dto.query.CategoryQueryDTO;
import com.ecommerce.product.entity.Category;
import com.ecommerce.product.exception.CustomException;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repository.CategoryRespository;
import com.ecommerce.product.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private CategoryRespository categoryRepository;

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Autowired
    public void setCategoryRepository(CategoryRespository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Async
    @Transactional
    public CompletableFuture<Iterable<CategoryQueryDTO>> getAllCategories() {
        LOGGER.info("CategoryService.getAllCategories");
        return CompletableFuture.completedFuture("getAllCategories")
                .thenApplyAsync(v -> {
                    List<CategoryQueryDTO> ret = new ArrayList<>();

                    categoryRepository.findAll().forEach(c -> {
                        CategoryQueryDTO dto = CategoryQueryDTO
                                .builder()
                                .id(c.getId())
                                .name(c.getName())
                                .build();
                        ret.add(dto);
                    });
                    return ret;
                });
    }

    @Async
    @Transactional
    public CompletableFuture<CategoryQueryDTO> getCategory(Integer id) {
        LOGGER.info("CategoryService.getCategory {}", id);
        return CompletableFuture.completedFuture(id)
                .thenApplyAsync(k -> {
                    Category category = categoryRepository
                            .findById(id)
                            .orElseThrow(() -> new CustomException(ErrorConstant.CATEGORY_NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND));
                    return mapper.buildCategoryQuery(category);
                });
    }
}
