package com.ecommerce.product.service.product.impl;

import com.ecommerce.product.dto.form.ProductFormDTO;
import com.ecommerce.product.dto.query.ProductQueryDTO;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.exception.CustomException;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repository.ProductRepository;
import com.ecommerce.product.service.product.ProductService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ecommerce.product.constants.ErrorConstant.IDENTIFIER_IS_REQUIRED_EXCEPTION;
import static com.ecommerce.product.constants.ErrorConstant.PRODUCT_FORM_DATA_IS_REQUIRED;
import static com.ecommerce.product.constants.ErrorConstant.PRODUCT_NOT_FOUND_EXCEPTION;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public ProductFormDTO create(ProductFormDTO formDTO) {
        if (formDTO == null) {
            throw new CustomException(PRODUCT_FORM_DATA_IS_REQUIRED, HttpStatus.BAD_REQUEST);
        }

        return ProductMapper.convertToFormDto(productRepository.save(ProductMapper.convertToModel(formDTO)));
    }

    @Transactional
    @Override
    public ProductFormDTO update(ProductFormDTO formDTO) {
        if (formDTO == null) {
            throw new CustomException(PRODUCT_FORM_DATA_IS_REQUIRED, HttpStatus.BAD_REQUEST);
        }

        if (ObjectUtils.isEmpty(formDTO.getId())) {
            throw new CustomException(IDENTIFIER_IS_REQUIRED_EXCEPTION, HttpStatus.BAD_REQUEST);
        }

        boolean isExist = productRepository.existsById(formDTO.getId());
        if (!isExist) {
            throw new CustomException(PRODUCT_NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND);
        }

        return ProductMapper.convertToFormDto(productRepository.save(ProductMapper.convertToModel(formDTO)));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new CustomException(IDENTIFIER_IS_REQUIRED_EXCEPTION, HttpStatus.BAD_REQUEST);
        }

        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductQueryDTO> getAll(PageRequest pr) {
        return productRepository.findAll(pr).map(ProductMapper::convertToQueryDto);
    }

    @Override
    public ProductQueryDTO getOne(Integer id) {
        if (ObjectUtils.isEmpty(id)) {
            throw new CustomException(IDENTIFIER_IS_REQUIRED_EXCEPTION, HttpStatus.BAD_REQUEST);
        }

        return ProductMapper.convertToQueryDto(productRepository.findById(id)
                .orElseThrow(() -> new CustomException(PRODUCT_NOT_FOUND_EXCEPTION, HttpStatus.NOT_FOUND)));
    }
}
