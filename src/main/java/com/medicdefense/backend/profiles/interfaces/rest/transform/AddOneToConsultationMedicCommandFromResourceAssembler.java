package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.AddOneToConsultationMedicsMadeCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

public class AddOneToConsultationMedicCommandFromResourceAssembler {
    public static AddOneToConsultationMedicsMadeCommand ToCommandFromResource(MedicDefenseRecordId recordId) {
        return new AddOneToConsultationMedicsMadeCommand(recordId);
    }
}
