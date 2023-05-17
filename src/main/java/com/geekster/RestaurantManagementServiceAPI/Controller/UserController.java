package com.geekster.RestaurantManagementServiceAPI.Controller;

import com.geekster.RestaurantManagementServiceAPI.Model.FoodItem;
import com.geekster.RestaurantManagementServiceAPI.Service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    public ResponseEntity<FoodItem> createFoodItem(@RequestBody FoodItem foodItem) {
        FoodItem createdFoodItemDTO = foodItemService.createFoodItem(foodItem);
        return ResponseEntity.ok(createdFoodItemDTO);
    }

    @GetMapping("/{foodItemId}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable String foodItemId) {
        FoodItem foodItemDTO = foodItemService.getFoodItemById(foodItemId);
        return ResponseEntity.ok(foodItemDTO);
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        List<FoodItem> foodItemDTOs = foodItemService.getAllFoodItems();
        return ResponseEntity.ok(foodItemDTOs);
    }

    @PutMapping("/{foodItemId}")
    public ResponseEntity<FoodItem> updateFoodItem(@PathVariable String foodItemId, @RequestBody FoodItem foodItemDTO) {
        FoodItem updatedFoodItemDTO = foodItemService.updateFoodItem(foodItemId, foodItemDTO);
        return ResponseEntity.ok(updatedFoodItemDTO);
    }

    @DeleteMapping("/{foodItemId}")
    public ResponseEntity<Void> deleteFoodItemById(@PathVariable String foodItemId) {
        foodItemService.deleteFoodItemById(foodItemId);
        return ResponseEntity.ok().build();
    }
}
