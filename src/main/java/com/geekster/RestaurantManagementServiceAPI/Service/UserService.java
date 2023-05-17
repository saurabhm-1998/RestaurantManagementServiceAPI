package com.geekster.RestaurantManagementServiceAPI.Service;

import com.geekster.RestaurantManagementServiceAPI.DTO.SignInInput;
import com.geekster.RestaurantManagementServiceAPI.DTO.SignInOutput;
import com.geekster.RestaurantManagementServiceAPI.DTO.SignUpInput;
import com.geekster.RestaurantManagementServiceAPI.DTO.SignUpOutput;
import com.geekster.RestaurantManagementServiceAPI.Model.User;
import com.geekster.RestaurantManagementServiceAPI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public SignUpOutput signUpUser(SignUpInput signUpInputDTO) {
        // Validate email format
        if (!signUpInputDTO.getEmail().matches("^(.+)@(.+)$")) {
            throw new RuntimeException("Invalid email format");
        }

        // Check if email is already registered
        if (userRepository.findByEmail(signUpInputDTO.getEmail()) != null) {
            throw new RuntimeException("Email is already registered");
        }

        // Check if admin user
        if (signUpInputDTO.getEmail().endsWith("@admin.com")) {
            throw new RuntimeException("Cannot sign up as an admin user");
        }

        // Create a new user entity
        User user = new User();
        user.setUsername(signUpInputDTO.getUsername());
        user.setPassword(signUpInputDTO.getPassword());
        user.setEmail(signUpInputDTO.getEmail());

        // Save the user in the database
        User savedUser = userRepository.save(user);

        // Create a response DTO
        SignUpOutput signUpOutputDTO = new SignUpOutput();
        signUpOutputDTO.setUserId(savedUser.getId());

        return signUpOutputDTO;
    }

    public SignInOutput signInUser(SignInInput signInInput) {
        // Validate credentials
        User user = userRepository.findByUsername(signInInput.getUsername());
        if (user == null || !user.getPassword().equals(signInInput.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Generate authentication token
        String token = TokenAuthenticationUtil.generateToken();

        // Save the token in user entity
        user.setToken(token);
        userRepository.save(user);

        // Create a response DTO
        SignInOutput signInOutputDTO = new SignInOutput();
        signInOutputDTO.setToken(token);

        return signInOutputDTO;
    }

    public User getUserById(Long userId) {
        // Check if user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create a response DTO
        User userDTO = new User();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());

        return user;
    }

    public List<User> getAllUsers() {
        // Get all users from the database
        List<User> users = userRepository.findAll();

        // Create a list of response DTOs
        List<User> userDTOs = new ArrayList<>();
        for (User user : users) {
            User userDTO = new User();
            userDTO.setUsername(user.getUsername());
            userDTO.setEmail(user.getEmail());
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    public void deleteUserById(String userId) {
        // Check if user exists
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new RuntimeException(("User not found"))
        }

        // Delete the user
        userRepository.delete(optionalUser.get());
    }
}
