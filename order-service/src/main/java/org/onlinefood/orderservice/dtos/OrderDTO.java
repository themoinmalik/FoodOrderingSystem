package org.onlinefood.orderservice.dtos;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.onlinefood.orderservice.models.OrderItem;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {


    private Long userId;

    private Long restaurantId;

    private Date orderDate;

    private String status;

    private List<OrderItem> orderItems;


}
