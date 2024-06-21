package com.medicdefense.backend.profiles.interfaces.rest.resources;

public record CreateLawyerResource (
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String image_url,
        String DNI,
        int yearsExperience,
        int casesWon,
        int price){
}
