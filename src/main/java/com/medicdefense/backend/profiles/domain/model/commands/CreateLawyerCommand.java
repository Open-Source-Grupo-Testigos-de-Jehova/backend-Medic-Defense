package com.medicdefense.backend.profiles.domain.model.commands;

public record CreateLawyerCommand(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String image_url,
        String DNI,
        int yearsExperience,
        int casesWon,
        int price
) {
}
