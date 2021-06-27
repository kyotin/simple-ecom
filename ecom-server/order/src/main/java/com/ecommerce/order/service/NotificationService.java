package com.ecommerce.order.service;

import com.ecommerce.order.dto.PlaceOrderDTO;

public interface NotificationService {
    /**
     * This method will send message to queue for another service
     * send sms/email to notify customer/seller
     */
    void notifyOrderChangeStatus(PlaceOrderDTO msg);
}
