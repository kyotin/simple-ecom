package com.ecommerce.product.service;


import com.ecommerce.product.dto.form.BaseFormDTO;
import com.ecommerce.product.dto.query.BaseQueryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BaseService<Q extends BaseQueryDTO, F extends BaseFormDTO> {
    F create(F f);

    F update(F f);

    void delete(Integer id);

    Page<Q> getAll(PageRequest pr);

    Q getOne(Integer id);
}
