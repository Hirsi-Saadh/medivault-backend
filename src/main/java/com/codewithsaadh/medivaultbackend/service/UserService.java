package com.codewithsaadh.medivaultbackend.service;

import com.codewithsaadh.medivaultbackend.method.JwtUtil;
import com.codewithsaadh.medivaultbackend.method.PasswordHashing;
import com.codewithsaadh.medivaultbackend.model.User;
import com.codewithsaadh.medivaultbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    private PasswordHashing passwordHashing;

    public User createUser(String username, String uid, String email, User.UserType usertype) {
        // Check if the username already exists
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("Username already exists");
        }

        // Create a new user object and set its fields
        User user = new User();
        user.setUsername(username);
        user.setUid(uid);
//        user.setPassword(passwordHashing.hashPassword(password));
        user.setEmail(email);
        user.setUserType(usertype);

        // Save the user to the database
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long userId, User updatedUser) {
        // Retrieve the user by ID from the database
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            // Update the existing user's properties with the values from the updatedUser object
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setUserType(updatedUser.getUserType());
            // Save the updated user to the database
            return userRepository.save(existingUser);
        }
        return null;
    }

    public String login(String username, String password, User.UserType usertype) {
        // Find the user in the database by the provided username
        User user = userRepository.findByUsername(username);

        if (user != null && passwordHashing.hashPassword(password).equals(user.getPassword()) && user.getUserType().equals(usertype)) {
            // If the login is successful, generate and return the JWT token
            return jwtUtil.generateToken(user);
        }

        // Return null if login failed
        return null;
    }


    public boolean deleteUser(Long userId) {
        // Check if the user exists in the database
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    // Other methods for updating and deleting users can be added here

    // You can also add methods to get users by username, email, etc., based on your requirements

    // Additional business logic can be added as needed

    // Getters and Setters, if required
}

