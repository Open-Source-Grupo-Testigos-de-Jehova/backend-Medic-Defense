package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.UpdateWonCasesCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UpdateWonCasesResource;

public class UpdateWonCasesCommandFromResourceAssembler {
    public static UpdateWonCasesCommand fromResource(MedicDefenseRecordId recordId, UpdateWonCasesResource resource) {
        return new UpdateWonCasesCommand(resource.wonCases(), recordId);
    }
}
