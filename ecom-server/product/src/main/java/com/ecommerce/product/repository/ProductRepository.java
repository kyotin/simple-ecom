package com.ecommerce.product.repository;

import com.ecommerce.product.entity.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Cacheable(cacheNames = "productsWithPaging")
    Page<Product> findAll(Pageable pageable);

    @Cacheable(value = "product", key = "#integer")
    Optional<Product> findById(Integer integer);

    @CachePut(value = "product", key = "#entity.id")
    @CacheEvict(cacheNames = "productsWithPaging", allEntries = true)
    <S extends Product> S save(S entity);

    @Caching(evict = {
            @CacheEvict(value = "product", key = "#integer"),
            @CacheEvict(cacheNames = "productsWithPaging", allEntries = true)
    })
    void deleteById(Integer integer);

    Page<Product> findAllByCategoryId(Pageable pageable, Integer categoryId);
}
