package com.geekster.RestaurantManagementServiceAPI.Repository;

import com.geekster.RestaurantManagementServiceAPI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
