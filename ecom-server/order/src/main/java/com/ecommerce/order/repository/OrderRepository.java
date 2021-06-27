package com.ecommerce.order.repository;

import com.ecommerce.order.entity.PlaceOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<PlaceOrder, Integer> {
    Page<PlaceOrder> findOrdersByBuyerId(Pageable pageable, Integer buyerId);
}
