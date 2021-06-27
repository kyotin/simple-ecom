package com.ecommerce.order.service.impl;

import com.ecommerce.order.dto.PlaceOrderDTO;
import com.ecommerce.order.entity.PlaceOrder;
import com.ecommerce.order.exception.CustomException;
import com.ecommerce.order.mapper.OrderServiceMapper;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderServiceImpl implements OrderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private final OrderServiceMapper mapper = OrderServiceMapper.INSTANCE;

    @Async
    @Transactional
    public CompletableFuture<PlaceOrderDTO> placeOrder(PlaceOrderDTO placeOrderDTO) {
        if (!placeOrderDTO.isValid()) {
            throw new CustomException("request is not valid", HttpStatus.BAD_REQUEST);
        }

        LOGGER.info("running to here");
        placeOrderDTO.calculatePrice();

        PlaceOrder placeOrder = orderRepository.save(mapper.buildPlaceOrder(placeOrderDTO));

        return CompletableFuture.completedFuture(mapper.buildPlaceOrderDTO(placeOrder));
    }
}
