package com.example.demo.repository;

import com.example.demo.entity.TravelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepository extends JpaRepository<TravelDetails,Long> {
    List<TravelDetails> findTravelDetailsById(Long id);
}
