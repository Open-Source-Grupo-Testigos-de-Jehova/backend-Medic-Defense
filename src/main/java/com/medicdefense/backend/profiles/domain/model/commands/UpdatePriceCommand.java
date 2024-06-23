package com.medicdefense.backend.profiles.domain.model.commands;

import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

public record UpdatePriceCommand(int Price, MedicDefenseRecordId recordId){
}
