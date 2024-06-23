package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.AddOneToConsultationMedicStudentMadeCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

public class AddOneToConsultationMedicStudentCommandFromResourceAssembler {
    public static AddOneToConsultationMedicStudentMadeCommand ToCommandFromResource(MedicDefenseRecordId recordId) {
        return new AddOneToConsultationMedicStudentMadeCommand(recordId);
    }
}
