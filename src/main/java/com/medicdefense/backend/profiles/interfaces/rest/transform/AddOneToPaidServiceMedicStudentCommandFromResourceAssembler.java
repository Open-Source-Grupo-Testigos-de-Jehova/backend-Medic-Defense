package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.AddOneToPaidServiceMedicStudentCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

public class AddOneToPaidServiceMedicStudentCommandFromResourceAssembler {
    public static AddOneToPaidServiceMedicStudentCommand ToCommandFromResource(MedicDefenseRecordId recordId) {
        return new AddOneToPaidServiceMedicStudentCommand(recordId);
    }
}
