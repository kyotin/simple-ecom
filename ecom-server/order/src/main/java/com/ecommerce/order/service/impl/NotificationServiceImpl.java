package com.ecommerce.order.service.impl;

import com.ecommerce.order.dto.PlaceOrderDTO;
import com.ecommerce.order.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private JmsTemplate sender;

    @Autowired
    public void setSender(JmsTemplate sender) {
        this.sender = sender;
    }

    @Async
    public void notifyOrderChangeStatus(PlaceOrderDTO msg) {
        sender.convertAndSend("/queue/order_change_status", msg);
    }
}
