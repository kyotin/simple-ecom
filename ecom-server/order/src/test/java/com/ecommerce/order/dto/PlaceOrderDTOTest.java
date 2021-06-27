package com.ecommerce.order.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceOrderDTOTest {
    @Test
    void testParseJson() {
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
                "      \"price\": 21230.0,\n" +
                "      \"productId\": 1,\n" +
                "      \"quantity\": 10\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        try {
            PlaceOrderDTO dto = mapper.readValue(data, PlaceOrderDTO.class);
            assertEquals("nguyentrongtin89@gmail.com", dto.getBuyer().getEmail());
            assertEquals("0908235566", dto.getBuyer().getPhoneNumber());
            assertEquals(1, dto.getProducts().size());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}