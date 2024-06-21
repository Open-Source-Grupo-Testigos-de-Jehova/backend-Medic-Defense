package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.Medic;
import com.medicdefense.backend.profiles.domain.model.commands.CreateMedicCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

import java.util.Optional;

public interface MedicCommandService {
    MedicDefenseRecordId handle(CreateMedicCommand command);
}
