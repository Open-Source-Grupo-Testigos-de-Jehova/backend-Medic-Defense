package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.domain.model.commands.CreateLawyerCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

import java.util.Optional;

public interface LawyerCommandService {
    MedicDefenseRecordId handle(CreateLawyerCommand command);
}
