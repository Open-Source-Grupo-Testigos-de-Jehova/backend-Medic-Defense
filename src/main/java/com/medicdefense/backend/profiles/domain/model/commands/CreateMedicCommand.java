package com.medicdefense.backend.profiles.domain.model.commands;

public record CreateMedicCommand(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String image_url,
        String DNI
        ){}
