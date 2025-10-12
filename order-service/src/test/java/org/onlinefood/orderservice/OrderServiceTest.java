package org.onlinefood.orderservice;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.onlinefood.orderservice.dtos.OrderDTO;
import org.onlinefood.orderservice.models.Order;
import org.onlinefood.orderservice.repos.OrderRepo;
import org.onlinefood.orderservice.services.OrderService;

import java.util.Date;
import java.util.Optional;

public class OrderServiceTest {

    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateOrderStatus() {
        // Arrange
        Order order = new Order();
        order.setId(123L);
        order.setStatus("Pending");

        Order updatedOrder = new Order();
        updatedOrder.setId(123L);
        updatedOrder.setStatus("Completed");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setStatus(order.getStatus());
        orderDTO.setOrderItems(order.getOrderItems());

        when(orderRepo.findById(123L)).thenReturn(Optional.of(order));
        when(orderRepo.save(any(Order.class))).thenReturn(updatedOrder);

        // Act
        Order result = orderService.updateOrderStatus(orderDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Completed", result.getStatus());
        verify(orderRepo, times(1)).findById(123L);
        verify(orderRepo, times(1)).save(any(Order.class));
    }
}

