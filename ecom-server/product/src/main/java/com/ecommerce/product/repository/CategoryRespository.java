package com.ecommerce.product.repository;

import com.ecommerce.product.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRespository extends CrudRepository<Category, Integer> {
}
