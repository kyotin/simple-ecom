package com.ecommerce.order.service.impl;

import com.ecommerce.order.dto.PlaceOrderDTO;
import com.ecommerce.order.entity.PlaceOrder;
import com.ecommerce.order.exception.CustomException;
import com.ecommerce.order.mapper.OrderServiceMapper;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.service.NotificationService;
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

    private NotificationService notificationService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
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

        return CompletableFuture
                .completedFuture(mapper.buildPlaceOrder(placeOrderDTO))
                .thenApplyAsync(placeOrder -> {
                    final PlaceOrder order = orderRepository.save(placeOrder);
                    final PlaceOrderDTO dto = mapper.buildPlaceOrderDTO(order);
                    notificationService.notifyOrderChangeStatus(dto);
                    return dto;
                });
    }

    @Override
    public CompletableFuture<PlaceOrderDTO> approveOrder(PlaceOrderDTO placeOrderDTO) {
        throw new UnsupportedOperationException("will do if i have time :)");
    }


}
