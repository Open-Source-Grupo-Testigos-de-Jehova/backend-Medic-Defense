package com.medicdefense.backend.profiles.interfaces.rest.resources;

public record CreateLawyerResource (
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String DNI,
        String image_url,
        int yearsExperience,
        int casesWon,
        int price){
}
