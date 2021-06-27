package com.ecommerce.order.mapper;

import com.ecommerce.order.dto.BuyerDTO;
import com.ecommerce.order.dto.OrderProductDTO;
import com.ecommerce.order.dto.PlaceOrderDTO;
import com.ecommerce.order.entity.Buyer;
import com.ecommerce.order.entity.OrderProduct;
import com.ecommerce.order.entity.PlaceOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderServiceMapper {
    OrderServiceMapper INSTANCE = Mappers.getMapper(OrderServiceMapper.class);

    PlaceOrder buildPlaceOrder(PlaceOrderDTO dto);

    PlaceOrderDTO buildPlaceOrderDTO(PlaceOrder placeOrder);

    OrderProductDTO buildOrderProductDTO(OrderProduct orderProduct);

    OrderProduct buildOrderProduct(OrderProductDTO dto);

    Buyer buildBuyer(BuyerDTO dto);

    BuyerDTO buildBuyerDTO(Buyer buyer);

}
