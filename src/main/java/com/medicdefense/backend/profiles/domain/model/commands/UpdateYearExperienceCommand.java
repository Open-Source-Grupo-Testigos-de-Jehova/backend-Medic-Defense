package com.medicdefense.backend.profiles.domain.model.commands;

import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

public record UpdateYearExperienceCommand(int YearExperience, MedicDefenseRecordId recordId){
}
