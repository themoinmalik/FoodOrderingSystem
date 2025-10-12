package org.onlinefood.orderservice.services;


import lombok.RequiredArgsConstructor;
import org.onlinefood.orderservice.dtos.OrderDTO;
import org.onlinefood.orderservice.dtos.UserDto;
import org.onlinefood.orderservice.exceptions.ResourceNotFoundException;
import org.onlinefood.orderservice.models.Order;
import org.onlinefood.orderservice.models.OrderItem;
import org.onlinefood.orderservice.repos.OrderRepo;
import org.onlinefood.orderservice.securityconfig.JwtUtilClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Logger log = Logger.getLogger(OrderService.class.getName());

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private JwtUtilClient jwtUtil;

    @Autowired
    private JavaMailSender mailSender;

    public Order createOrder(OrderDTO orderDTO) {

        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setRestaurantId(orderDTO.getRestaurantId());
        order.setOrderItems(orderDTO.getOrderItems());
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        if (order.getOrderItems() != null) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
            }
        } else {
            throw new IllegalArgumentException("Order items cannot be null");
        }
        log.info("Order created: 2 ");
        sendEmail(orderDTO.getUserId());
        return orderRepo.save(order);
    }

    private void sendEmail(Long userId) {

        //fetch user by userId....
        UserDto userDto = jwtUtil.getUserById(userId);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("themoinmalik@gmail.com");
        mailMessage.setTo(userDto.getEmail());
        mailMessage.setSubject("Order created");
        mailMessage.setText("Order Created");
        mailSender.send(mailMessage);
        log.info("Mail Sent : " + userDto.getEmail());
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id " + id));
    }

    public Order getOrdersByUserId(Long userId) {
        return orderRepo.findByUserId(userId);
    }


    public Order updateOrderStatus(OrderDTO orderDTO) {

        Order order = orderRepo.findByUserId(orderDTO.getUserId());

        Order existingOrder = orderRepo.findById(order.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + order.getId()));
        existingOrder.setStatus(order.getStatus());
        return orderRepo.save(existingOrder);
    }
}
