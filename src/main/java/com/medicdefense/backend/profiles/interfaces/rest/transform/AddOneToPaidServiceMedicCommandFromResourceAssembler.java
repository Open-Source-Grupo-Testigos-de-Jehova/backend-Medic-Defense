package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.AddOneToPaidServiceMedicCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

public class AddOneToPaidServiceMedicCommandFromResourceAssembler {
    public static AddOneToPaidServiceMedicCommand ToCommandFromResource(MedicDefenseRecordId recordId) {
        return new AddOneToPaidServiceMedicCommand(recordId);
    }
}
