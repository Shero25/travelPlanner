package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TravelDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String activities;
    private String image;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
