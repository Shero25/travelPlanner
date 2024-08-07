package com.example.demo.service;

import com.example.demo.entity.TravelDetails;

import java.util.List;

public interface TravelService {

    TravelDetails saveTravelInfo(TravelDetails travelDetails, Long userId);
    List<TravelDetails> getAll();
    TravelDetails getTravelDetailsById(Long id);
    void updateUser(Long id, TravelDetails travelDetails);
    List<TravelDetails> getTravelDetailsByUserId(Long id);
    void DeleteMovie(Long id);
    List<TravelDetails> getUpcomingTrips(Long userId, int days);
    void deleteTravelDetailsByUserIdAndTripId(Long userId, Long tripId);

}
