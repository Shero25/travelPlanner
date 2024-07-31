package com.example.demo.service;

import com.example.demo.entity.TravelDetails;
import com.example.demo.entity.User;
import com.example.demo.repository.TravelRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TravelServiceImpl implements TravelService {

    @Autowired
    private TravelRepository travelRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public TravelDetails saveTravelInfo(TravelDetails travelDetails, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            travelDetails.setUser(optionalUser.get());
            return travelRepository.save(travelDetails);
        } else {
            throw new RuntimeException("User with ID: " + userId + " not found");
        }
    }


    @Override
    public List<TravelDetails> getAll() {
        return travelRepository.findAll();
    }

    @Override
    public TravelDetails getTravelDetailsById(Long id) {
        Optional<TravelDetails> optional = travelRepository.findById(id);
        TravelDetails travelDetails;
        if(optional.isPresent()){
            travelDetails =optional.get();
        }else {
            throw new RuntimeException("User for the id:" +id+ "is not found");
        }
        return travelDetails;
    }


    @Override
    public void updateUser(Long id, TravelDetails travelDetails) {
       TravelDetails travelDetails1 = travelRepository.findById(id).get();
        travelDetails1.setDescription(travelDetails.getDescription());
        travelDetails1.setActivities(travelDetails.getActivities());
        travelDetails1.setStartDate(travelDetails.getStartDate());
        travelDetails1.setEndDate(travelDetails.getEndDate());
        travelRepository.save(travelDetails1);
    }

    @Override
    public List<TravelDetails> getTravelDetailsByUserId(Long userId) {
        return travelRepository.findByUserId(userId);
    }

    @Override
    public void DeleteMovie(Long id) {
        this.travelRepository.deleteById(id);

    }
    @Override
    public void deleteTravelDetailsByUserIdAndTripId(Long userId, Long tripId) {
        TravelDetails travelDetails = travelRepository.findByIdAndUserId(tripId, userId);
        if (travelDetails != null) {
            travelRepository.delete(travelDetails);
        } else {
            throw new RuntimeException("Travel details not found for user ID: " + userId + " and trip ID: " + tripId);
        }
    }
    @Override
    public List<TravelDetails> getUpcomingTrips(Long userId, int days) {
        List<TravelDetails> userTrips = travelRepository.findByUserId(userId);
        LocalDate today = LocalDate.now();
        LocalDate upcomingDate = today.plusDays(days);
        return userTrips.stream()
                .filter(trip -> trip.getStartDate().isAfter(today) && trip.getStartDate().isBefore(upcomingDate))
                .toList();
    }
}
