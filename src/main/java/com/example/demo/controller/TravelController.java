package com.example.demo.controller;

import com.example.demo.entity.TravelDetails;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TravelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth/travel")
public class TravelController {

    @Autowired
    private TravelServiceImpl travelService; // Autowire UserInfoServiceImpl for handling user info operations

    @Autowired
    private UserRepository userRepository; // Autowire UserRepository for user-related database operations

    // GET endpoint to fetch all users' information
    @GetMapping
    public List<TravelDetails> findAll() {
        return travelService.getAll();
    }

    @PostMapping//request to send data in the database
    public void save(@RequestBody TravelDetails travelDetails) {//Request body is the information we want to save
        travelService.saveTravelInfo(travelDetails);
    }

    @GetMapping("/userInfo/{id}")
    public TravelDetails findOne(@PathVariable Long id) {
        return travelService.getTravelDetailsById(id); // Call service method to get user info by ID
    }

    // PUT endpoint to update user information by ID
    @PutMapping("/userInfo/{id}")
    public void update(@PathVariable Long id, @RequestBody TravelDetails travelDetails) {
        this.travelService.updateUser(id, travelDetails); // Call service method to update user info
    }

    // GET endpoint to fetch user profiles by user ID
    @GetMapping("/userInfo/users/{id}")
    public List<TravelDetails> getProfileByUserId(@PathVariable Long id) {
        return travelService.getTravelDetailsByUserId(id); // Call service method to get profiles by user ID
    }

}
