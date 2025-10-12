package org.onlinefood.orderservice.controllers;


import org.onlinefood.orderservice.dtos.OrderDTO;
import org.onlinefood.orderservice.models.Order;
import org.onlinefood.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    public static final Logger log = Logger.getLogger(OrderController.class.getName());

    @Autowired
    private OrderService orderService;


    @PostMapping
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        log.info("Order creating: 1 ");
        return orderService.createOrder(orderDTO);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        log.info("Get order by id {}" + id);
        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public Order getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }


    //admin can update order..
    @PostMapping("/updateStatus")
    public Order updateOrderStatus(@RequestBody OrderDTO orderDTO) {
        log.info("Order updating");
        return orderService.updateOrderStatus(orderDTO);
    }

}
