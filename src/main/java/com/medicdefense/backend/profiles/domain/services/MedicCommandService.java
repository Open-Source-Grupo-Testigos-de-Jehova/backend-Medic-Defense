package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.domain.model.aggregate.Medic;
import com.medicdefense.backend.profiles.domain.model.commands.AddOneToConsultationMedicsMadeCommand;
import com.medicdefense.backend.profiles.domain.model.commands.AddOneToPaidServiceMedicCommand;
import com.medicdefense.backend.profiles.domain.model.commands.CreateMedicCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

import java.util.Optional;

public interface MedicCommandService {
    Optional<MedicDefenseRecordId> handle(CreateMedicCommand command);
    Optional<Medic> handle(AddOneToConsultationMedicsMadeCommand command);
    Optional<Medic> handle(AddOneToPaidServiceMedicCommand command);
}
