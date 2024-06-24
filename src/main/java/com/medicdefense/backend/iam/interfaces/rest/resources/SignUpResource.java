package com.medicdefense.backend.iam.interfaces.rest.resources;

public record SignUpResource(
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
