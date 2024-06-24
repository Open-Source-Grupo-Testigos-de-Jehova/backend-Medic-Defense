package com.medicdefense.backend.profiles.interfaces.rest.resources;

public record LawyerResource (
        String MedicDefenseLawyerRecordId,
        Long profileId,
        int yearsExperience,
        int casesWon,
        int price){
}
