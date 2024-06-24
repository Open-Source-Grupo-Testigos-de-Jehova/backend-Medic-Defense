package com.medicdefense.backend.iam.interfaces.rest.resources;

public record AddNewLawyerResource(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String image_url,
        String DNI,
        String password,
        int yearsExperience,
        int casesWon,
        int price
) {
}
