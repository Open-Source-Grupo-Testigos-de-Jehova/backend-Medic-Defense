package com.medicdefense.backend.profiles.domain.model.commands;

public record CreateMedicStudentCommand(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String image_url,
        String DNI,
        String universityName
        ) {
}
