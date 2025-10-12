package org.onlinefood.notificationservice.consumer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "order-status", groupId = "notification-group")
    public void listen(String message) {
        // Parse the message and send notification
        emailService.sendEmail("user@example.com", "Order Update", message);
    }
}

