package com.medicdefense.backend.profiles.domain.model.commands;

public record UpdatePhoneNumberCommand(String phoneNumber, Long profileId) {
}
