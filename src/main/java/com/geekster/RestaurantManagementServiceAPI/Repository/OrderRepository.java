package com.geekster.RestaurantManagementServiceAPI.Repository;

import com.geekster.RestaurantManagementServiceAPI.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
