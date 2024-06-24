package com.medicdefense.backend.profiles.interfaces.rest.resources;

public record LawyerResource (
        String MedicDefenseLawyerRecordId,
        Long profileId,
        Long userId,
        int yearsExperience,
        int casesWon,
        int price){
}
