package com.geekster.RestaurantManagementServiceAPI.Controller;

import com.geekster.RestaurantManagementServiceAPI.Model.Order;
import com.geekster.RestaurantManagementServiceAPI.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        Order orderDTO = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderDTOs = orderService.getAllOrders();
        return ResponseEntity.ok(orderDTOs);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody Order orderDTO) {
        Order updatedOrderDTO = orderService.updateOrder(orderId, orderDTO);
        return ResponseEntity.ok(updatedOrderDTO);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrderById(@PathVariable String orderId) {
        orderService.deleteOrderById(orderId);
        return ResponseEntity.ok().build();
    }
}
