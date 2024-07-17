package com.example.demo.service;

import com.example.demo.entity.TravelDetails;
import com.example.demo.repository.TravelRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public TravelDetails saveTravelInfo(TravelDetails travelDetails) {
        return travelRepository.save(travelDetails);

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
        travelDetails1.setLocation(travelDetails.getLocation());
        travelDetails1.setDescription(travelDetails.getDescription());
        travelDetails1.setName(travelDetails.getName());
        travelDetails1.setActivities(travelDetails.getActivities());
        travelDetails1.setStartDate(travelDetails.getStartDate());
        travelDetails1.setEndDate(travelDetails.getEndDate());
        travelRepository.save(travelDetails1);
    }

    @Override
    public List<TravelDetails> getTravelDetailsByUserId(Long id) {
        return travelRepository.findTravelDetailsById(id);
    }

    @Override
    public void DeleteMovie(Long id) {
        this.travelRepository.deleteById(id);
    }
}
