package com.geekster.RestaurantManagementServiceAPI.Service;

import com.geekster.RestaurantManagementServiceAPI.Model.FoodItem;
import com.geekster.RestaurantManagementServiceAPI.Model.Order;
import com.geekster.RestaurantManagementServiceAPI.Model.OrderStatus;
import com.geekster.RestaurantManagementServiceAPI.Model.User;
import com.geekster.RestaurantManagementServiceAPI.Repository.FoodItemRepository;
import com.geekster.RestaurantManagementServiceAPI.Repository.OrderRepository;
import com.geekster.RestaurantManagementServiceAPI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    public Order createOrder(String userId, Long foodItemId, int quantity) {
        // Validate user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate food item
        FoodItem foodItem = foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        // Create a new order entity
        Order order = new Order();
        order.setFoodItem(foodItem);
        order.setQuantity(quantity);
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);

        // Save the order in the database
        Order savedOrder = orderRepository.save(order);

        return savedOrder;

    public Order getOrderById(Long orderId) {
        // Check if order exists
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return order;
    }

    public List<Order> getAllOrders() {
        // Get all orders from the database
        List<Order> orders = orderRepository.findAll();

        return orders;
    }

        public void updateOrderStatus(Long orderId, OrderStatus newStatus) {
            // Check if order exists
            Optional<Order> optionalOrder = orderRepository.findById(orderId);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                order.setStatus(newStatus);
                orderRepository.save(order);
            } else {
                throw new RuntimeException("Order not found");
            }
        }

        public void deleteOrderById(Long orderId) {
            // Check if order exists
            Optional<Order> optionalOrder = orderRepository.findById(orderId);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                orderRepository.delete(order);
            } else {
                throw new RuntimeException("Order not found");
            }
        }
}
