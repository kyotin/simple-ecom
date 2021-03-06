package com.ecommerce.product.service.impl;

import com.ecommerce.product.dto.form.ProductFormDTO;
import com.ecommerce.product.dto.query.ProductQueryDTO;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.CustomException;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

import static com.ecommerce.product.constants.ErrorConstant.INVALID_REQUEST_EXCEPTION;
import static com.ecommerce.product.constants.ErrorConstant.PRODUCT_FORM_DATA_IS_REQUIRED;
import static com.ecommerce.product.constants.ErrorConstant.PRODUCT_NOT_FOUND_EXCEPTION;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Async
    @Transactional
    public CompletableFuture<ProductFormDTO> create(ProductFormDTO formDTO) {
        LOGGER.info("creating product... {}", formDTO);
        if (formDTO == null) {
            throw new CustomException(PRODUCT_FORM_DATA_IS_REQUIRED, HttpStatus.BAD_REQUEST);
        }

        return CompletableFuture.completedFuture(mapper.buildProduct(formDTO))
                .thenApplyAsync(product -> {
                    final Product persistedProd = productRepository.save(product);
                    return mapper.buildProductForm(persistedProd);
                });
    }

    @Async
    @Transactional
    public CompletableFuture<ProductFormDTO> update(ProductFormDTO formDTO) {
        LOGGER.info("updating product... {}", formDTO);
        if (formDTO == null) {
            LOGGER.error("can't update product because input is null");
            throw new CustomException(PRODUCT_FORM_DATA_IS_REQUIRED, HttpStatus.BAD_REQUEST);
        }

        if (formDTO.isValid()) {
            LOGGER.error("can't update product because input is not valid. {}", formDTO);
            throw new CustomException(INVALID_REQUEST_EXCEPTION, HttpStatus.BAD_REQUEST);
        }

        boolean isExist = productRepository.existsById(formDTO.getId());
        if (!isExist) {
            LOGGER.error("can't update product, product not existed. {}", formDTO);
            throw new CustomException(INVALID_REQUEST_EXCEPTION, HttpStatus.NOT_FOUND);
        }

        return CompletableFuture.completedFuture(mapper.buildProduct(formDTO))
                .thenApplyAsync(product -> {
                    final Product persistedProd = productRepository.save(product);
                    return mapper.buildProductForm(persistedProd);
                });
    }

    @Transactional
    public void delete(Integer id) {
        if (id == null) {
            throw new CustomException(INVALID_REQUEST_EXCEPTION, HttpStatus.BAD_REQUEST);
        }
        productRepository.deleteById(id);
    }

    @Async
    public CompletableFuture<Page<ProductQueryDTO>> getAll(PageRequest pr) {
        LOGGER.info("getting all product...");
        return CompletableFuture.completedFuture("getAll")
                .thenApplyAsync(v -> productRepository.findAll(pr).map(mapper::buildProductQuery));
    }

    @Async
    public CompletableFuture<ProductQueryDTO> getOne(Integer id) {
        LOGGER.info("getting a product... {}", id);
        if (id == null) {
            throw new CustomException(INVALID_REQUEST_EXCEPTION, HttpStatus.BAD_REQUEST);
        }

        return CompletableFuture.completedFuture(id)
                .thenApplyAsync(k -> {
                    final Product product = productRepository.findById(k).orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND));
                    return mapper.buildProductQuery(product);
                });
    }

    @Async
    public CompletableFuture<Page<ProductQueryDTO>> getProductOfCategory(Pageable pageable, Integer categoryId) {
        LOGGER.info("getting all products of category... {}", categoryId);
        return CompletableFuture.completedFuture("getProductOfCagetory")
                .thenApplyAsync(v -> productRepository.findAllByCategoryId(pageable, categoryId).map(mapper::buildProductQuery));
    }
}
