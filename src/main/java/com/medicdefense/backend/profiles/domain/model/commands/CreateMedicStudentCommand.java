package com.medicdefense.backend.profiles.domain.model.commands;

public record CreateMedicStudentCommand(
        String userName,
        String password,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String image_url,
        String DNI,
        String universityName
        ) {
}
