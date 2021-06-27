package com.ecommerce.order.service;

import com.ecommerce.order.entity.OrderStatus;

public interface NotificationService {
    /**
     * This method will send message to queue for another service
     * send sms/email to notify customer/seller
     * @param from
     * @param to
     */
    void notify(OrderStatus from, OrderStatus to);
}
