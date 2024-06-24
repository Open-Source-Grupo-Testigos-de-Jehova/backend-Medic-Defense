package com.medicdefense.backend.profiles.interfaces.rest.resources;

public record MedicResource(
        String medicDefenseMedicRecordId,
        Long profileId,
        Long userId,
        int consultationsMade,
        int paidServices
) {
}
