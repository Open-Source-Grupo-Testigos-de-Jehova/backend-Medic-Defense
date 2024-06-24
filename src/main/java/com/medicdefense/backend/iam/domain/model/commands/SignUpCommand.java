package com.medicdefense.backend.iam.domain.model.commands;

public record SignUpCommand(
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String DNI,
        String image_url,
        String university,
        String role
) {
}
