package com.ecommerce.product.controller;

import com.ecommerce.product.constants.APIMappingConstant;
import com.ecommerce.product.dto.ResponseDTO;
import com.ecommerce.product.dto.form.ProductFormDTO;
import com.ecommerce.product.dto.query.ProductQueryDTO;
import com.ecommerce.product.service.product.ProductService;
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

@RestController
@RequestMapping(APIMappingConstant.PRODUCT)
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseDTO<ProductFormDTO>> create(@RequestBody ProductFormDTO formDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(HttpStatus.CREATED.getReasonPhrase(), "Success",
                        productService.create(formDTO)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO<ProductFormDTO>> update(@RequestBody ProductFormDTO formDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO<>(HttpStatus.CREATED.getReasonPhrase(), "Success",
                        productService.update(formDTO)));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO<Void>> delete(Integer id) {
        productService.delete(id);
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(),
                "Success"));
    }

    @GetMapping
    public ResponseEntity<Page<ProductQueryDTO>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(productService.getAll(PageRequest.of(page, pageSize)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<ProductQueryDTO>> getOne(@PathVariable Integer id) {
        return ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(),
                "Success", productService.getOne(id)));
    }
}
