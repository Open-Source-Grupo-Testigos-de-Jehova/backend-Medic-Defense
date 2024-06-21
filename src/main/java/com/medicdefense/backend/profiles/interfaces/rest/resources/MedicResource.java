package com.medicdefense.backend.profiles.interfaces.rest.resources;

public record MedicResource(String medicDefenseMedicRecordId, Long profileId, int consultationsMade, int paidServices) {
}
