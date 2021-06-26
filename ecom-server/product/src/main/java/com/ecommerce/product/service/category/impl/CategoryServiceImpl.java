package com.ecommerce.product.service.category.impl;

import com.ecommerce.product.dto.query.CategoryQueryDTO;
import com.ecommerce.product.repository.CategoryRespository;
import com.ecommerce.product.service.category.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public void setCategoryRepository(CategoryRespository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Async
    public CompletableFuture<Iterable<CategoryQueryDTO>> getAllCategories() {
        LOGGER.info("CategoryService.getAllCategories");
        List<CategoryQueryDTO> ret = new ArrayList<>();

        categoryRepository.findAll().forEach(c -> {
            CategoryQueryDTO dto = CategoryQueryDTO
                    .builder()
                    .id(c.getId())
                    .name(c.getName())
                    .build();
            ret.add(dto);
        });

        return CompletableFuture.completedFuture(ret);
    }
}
