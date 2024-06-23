package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.UpdatePriceCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UpdatePriceResource;

public class UpdatePriceCommandFromResourceAssembler {
    public static UpdatePriceCommand fromResource(MedicDefenseRecordId recordId, UpdatePriceResource resource) {
        return new UpdatePriceCommand(resource.Price(), recordId);
    }
}
