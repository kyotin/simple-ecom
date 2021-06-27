package com.ecommerce.order.repository;

import com.ecommerce.order.dto.PlaceOrderDTO;
import com.ecommerce.order.entity.Buyer;
import com.ecommerce.order.entity.PlaceOrder;
import com.ecommerce.order.entity.OrderProduct;
import com.ecommerce.order.entity.OrderStatus;
import com.ecommerce.order.mapper.OrderServiceMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {
    @Autowired
    private OrderRepository repository;

    private final OrderServiceMapper orderServiceMapper = OrderServiceMapper.INSTANCE;

    @Test
    void getOrder() throws Exception {
        PlaceOrder order = repository.findById(1).orElseThrow(() -> new Exception("can't not found order"));
        assertNotNull(order);

        Buyer buyer = order.getBuyer();
        assertNotNull(buyer);
        assertEquals("Tin Nguyen", buyer.getName());

        Set<OrderProduct> products = order.getProducts();
        assertTrue(products.size() > 0);
    }

    @Test
    void getAllOrderByBuyer() {
        Page<PlaceOrder> order = repository.findOrdersByBuyerId(PageRequest.of(0, 10), 1);
        assertTrue(order.getTotalElements() > 0);

        order.forEach(System.out::println);
    }

    @Test
    void placeOrder() throws JsonProcessingException {
        String data = "{\n" +
                "  \"buyer\": {\n" +
                "    \"email\": \"nguyentrongtin89@gmail.com\",\n" +
                "    \"homeAddress\": \"Era\",\n" +
                "    \"name\": \"Nguyen Trong Tin\",\n" +
                "    \"phoneNumber\": \"0908235566\"\n" +
                "  },\n" +
                "  \"products\": [\n" +
                "    {\n" +
                "      \"name\": \"KIA Morning\",\n" +
                "      \"price\": 21230,\n" +
                "      \"productId\": 1,\n" +
                "      \"quantity\": 10\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"NEW\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();
        PlaceOrderDTO placeOrderDTO = mapper.readValue(data, PlaceOrderDTO.class);
        placeOrderDTO.calculatePrice();

        final PlaceOrder placeOrder = orderServiceMapper.buildPlaceOrder(placeOrderDTO);
        PlaceOrder retOrder = repository.save(placeOrder);

        assertNotNull(retOrder);

        System.out.println(retOrder);
    }
}