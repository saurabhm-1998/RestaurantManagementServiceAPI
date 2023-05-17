package com.geekster.RestaurantManagementServiceAPI.Repository;

import com.geekster.RestaurantManagementServiceAPI.Model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
