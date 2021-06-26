package com.ecommerce.product.controller;

import com.ecommerce.product.constants.APIMappingConstant;
import com.ecommerce.product.dto.ResponseDTO;
import com.ecommerce.product.dto.form.ProductFormDTO;
import com.ecommerce.product.dto.query.ProductQueryDTO;
import com.ecommerce.product.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(APIMappingConstant.PRODUCT)
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<ResponseDTO<ProductFormDTO>>> create(@RequestBody ProductFormDTO formDTO) {
        return productService.create(formDTO)
                .thenApply(v -> ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(), "Success", v)))
                .exceptionally(throwable -> ResponseEntity.internalServerError().body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), throwable.getMessage())));
    }

    @PutMapping
    public CompletableFuture<ResponseEntity<ResponseDTO<ProductFormDTO>>> update(@RequestBody ProductFormDTO formDTO) {
        return productService.update(formDTO)
                .thenApply(v -> ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(), "Success", v)))
                .exceptionally(throwable -> ResponseEntity.internalServerError().body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), throwable.getMessage())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(), "Success"));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<ResponseDTO<Page<ProductQueryDTO>>>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                                                        @RequestParam(value = "categoryId", defaultValue = "0") int categoryId) {
        CompletableFuture<Page<ProductQueryDTO>> future =
                categoryId > 0 ? productService.getProductOfCategory(PageRequest.of(page, pageSize), categoryId) : productService.getAll(PageRequest.of(page, pageSize));

        return future
                .thenApply(v -> ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(), "Success", v)))
                .exceptionally(throwable -> ResponseEntity.internalServerError().body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), throwable.getMessage())));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<ResponseDTO<ProductQueryDTO>>> getOne(@PathVariable Integer id) {
        return productService.getOne(id)
                .thenApply(v -> ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(), "Success", v)))
                .exceptionally(throwable -> ResponseEntity.internalServerError().body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), throwable.getMessage())));
    }

}
