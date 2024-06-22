package com.medicdefense.backend.profiles.domain.model.commands;

import com.medicdefense.backend.profiles.domain.model.valueobjects.EmailAddress;

public record UpdateEmailCommand(String email, Long profileId) {
}
