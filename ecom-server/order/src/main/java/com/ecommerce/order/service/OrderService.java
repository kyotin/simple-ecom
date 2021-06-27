package com.ecommerce.order.service;

import com.ecommerce.order.dto.PlaceOrderDTO;

import java.util.concurrent.CompletableFuture;

public interface OrderService {
    /**
     * Place a new order
     * @param placeOrderDTO
     * @return
     */
    CompletableFuture<PlaceOrderDTO> placeOrder(PlaceOrderDTO placeOrderDTO);

    /**
     * Approve an order, after company receive order from
     * @param placeOrderDTO
     * @return
     */
    CompletableFuture<PlaceOrderDTO> approveOrder(PlaceOrderDTO placeOrderDTO);
}
