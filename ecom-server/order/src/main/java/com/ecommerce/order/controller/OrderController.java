package com.ecommerce.order.controller;

import com.ecommerce.order.constants.APIMappingConstant;
import com.ecommerce.order.dto.PlaceOrderDTO;
import com.ecommerce.order.dto.ResponseDTO;
import com.ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(APIMappingConstant.ORDER)
public class OrderController {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<PlaceOrderDTO>> placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        return orderService.placeOrder(placeOrderDTO)
                .thenApply(v -> ResponseEntity.ok(new ResponseDTO<>(HttpStatus.OK.getReasonPhrase(), "Success", v)))
                .exceptionally(throwable -> ResponseEntity.internalServerError().body(new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), throwable.getMessage())))
                .join();
    }

}
