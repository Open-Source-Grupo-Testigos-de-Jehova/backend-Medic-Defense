package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.UpdateYearExperienceCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UpdateYearExperienceResource;

public class UpdateYearExperienceCommandFromResourceAssembler {
    public static UpdateYearExperienceCommand fromResource(MedicDefenseRecordId recordId, UpdateYearExperienceResource resource) {
        return new UpdateYearExperienceCommand(resource.YearExperience(), recordId);
    }
}
