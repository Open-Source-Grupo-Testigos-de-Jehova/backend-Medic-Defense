package com.medicdefense.backend.iam.domain.model.commands;

public record AddANewLawyerCommand(
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
