package com.example.demo.controller;

import com.example.demo.auth.AuthenticationRequest;
import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    //We're posting the data from the register request to the database
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        //returns the status of the request(e.g ok means it was successful)
        return ResponseEntity.ok(service.register(request));
    }
    //We're posting the data from the AuthenticationRequest request to the database
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        //returns the status of the request(e.g ok means it was successful)
        return ResponseEntity.ok(service.authenticate(request));
    }
    //we're getting details of a user using id
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.findById(id));
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id, @RequestBody User updatedUser) {
        // Assuming you have a service that handles user updates
        User user=service.updateUser(id,updatedUser);

        if (user != null) {
            return ResponseEntity.ok(user); // Return updated user if successful
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }
    }
}
