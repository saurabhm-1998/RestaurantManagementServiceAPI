package com.geekster.RestaurantManagementServiceAPI.Service;

import com.geekster.RestaurantManagementServiceAPI.Model.FoodItem;
import com.geekster.RestaurantManagementServiceAPI.Repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    public FoodItem createFoodItem(FoodItem foodItemDTO) {
        // Check if the user is admin
        if (!userRole.equals("ADMIN")) {
            throw new RuntimeException("Only admin users can create food items");
        }

        // Create a new food item entity
        FoodItem foodItem = new FoodItem();
        foodItem.setTitle(foodItemDTO.getTitle());
        foodItem.setDescription(foodItemDTO.getDescription());
        foodItem.setPrice(foodItemDTO.getPrice());

        // Save the food item in the database
        FoodItem savedFoodItem = foodItemRepository.save(foodItem);

        // Create a response DTO
        FoodItem createdFoodItemDTO = new FoodItem();
        createdFoodItemDTO.setId(savedFoodItem.getId());
        createdFoodItemDTO.setTitle(savedFoodItem.getTitle());
        createdFoodItemDTO.setDescription(savedFoodItem.getDescription());
        createdFoodItemDTO.setPrice(savedFoodItem.getPrice());

        return createdFoodItemDTO;
    }

    public FoodItem getFoodItemById(Long foodItemId) {
        // Get the food item from the database
        FoodItem foodItem = foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        // Create a response DTO
        FoodItem foodItemDTO = new FoodItem();
        foodItemDTO.setId(foodItem.getId());
        foodItemDTO.setTitle(foodItem.getTitle());
        foodItemDTO.setDescription(foodItem.getDescription());
        foodItemDTO.setPrice(foodItem.getPrice());

        return foodItemDTO;
    }

    public List<FoodItem> getAllFoodItems() {
        // Get all food items from the database
        List<FoodItem> foodItems = foodItemRepository.findAll();

        // Create a list of response DTOs
        List<FoodItem> foodItemDTOs = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            FoodItem foodItemDTO = new FoodItem();
            foodItemDTO.setId(foodItem.getId());
            foodItemDTO.setTitle(foodItem.getTitle());
            foodItemDTO.setDescription(foodItem.getDescription());
            foodItemDTO.setPrice(foodItem.getPrice());
            foodItemDTOs.add(foodItemDTO);
        }

        return foodItemDTOs;
    }

    public FoodItem updateFoodItem(Long foodItemId, FoodItem foodItemDTO, String userRole) {
        // Check if the user is admin
        if (!userRole.equals("ADMIN")) {
            throw new RuntimeException("Only admin users can update food items");
        }

        // Get the existing food item from the database
        FoodItem existingFoodItem = foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        // Update the properties of the food item
        existingFoodItem.setTitle(foodItemDTO.getTitle());
        existingFoodItem.setDescription(foodItemDTO.getDescription());
        existingFoodItem.setPrice(foodItemDTO.getPrice());

        // Save the updated food item in the database
        FoodItem updatedFoodItem = foodItemRepository.save(existingFoodItem);

        // Create a response DTO
        FoodItem updatedFoodItemDTO = new FoodItem();
        updatedFoodItemDTO.setTitle(updatedFoodItem.getTitle());
        updatedFoodItemDTO.setDescription(updatedFoodItem.getDescription());
        updatedFoodItemDTO.setPrice(updatedFoodItem.getPrice());

        return updatedFoodItemDTO;
    }

    public void deleteFoodItem(Long foodItemId, String userRole) {
        // Check if the user is admin
        if (!userRole.equals("ADMIN")) {
            throw new RuntimeException("Only admin users can delete food items");
        }

        // Check if the food item exists
        if (!foodItemRepository.existsById(foodItemId)) {
            throw new RuntimeException("Food item not found");
        }

        // Delete the food item from the database
        foodItemRepository.deleteById(foodItemId);
    }
}
