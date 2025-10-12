package org.onlinefood.orderservice.repos;


import org.onlinefood.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findByUserId(Long userId);

}
