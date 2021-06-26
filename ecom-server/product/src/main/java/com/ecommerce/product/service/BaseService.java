package com.ecommerce.product.service;


import com.ecommerce.product.dto.form.BaseFormDTO;
import com.ecommerce.product.dto.query.BaseQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.concurrent.CompletableFuture;

public interface BaseService<Q extends BaseQueryDTO, F extends BaseFormDTO> {
    CompletableFuture<F> create(F f);

    CompletableFuture<F> update(F f);

    void delete(Integer id);

    Page<Q> getAll(PageRequest pr);

    CompletableFuture<Q> getOne(Integer id);
}
