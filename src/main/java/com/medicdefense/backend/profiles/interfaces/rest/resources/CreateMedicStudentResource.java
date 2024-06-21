package com.medicdefense.backend.profiles.interfaces.rest.resources;

public record CreateMedicStudentResource(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String image_url,
        String DNI,
        String universityName
) {
}
