package com.codewithsaadh.medivaultbackend.controller;

import com.codewithsaadh.medivaultbackend.model.User;
import com.codewithsaadh.medivaultbackend.repository.UserRepository;
import com.codewithsaadh.medivaultbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user.getUsername(), user.getUid(), user.getEmail(), user.getUserType());
    }

    @GetMapping("/usertype")
    public ResponseEntity<User> getUserByUid(@RequestParam("uid") String uid) {
        User user = userService.findUserByUid(uid);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Perform any cleanup or session invalidation here
        return ResponseEntity.ok("Logged out successfully");
    }



    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        return userService.updateUser(userId, updatedUser);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }
}
