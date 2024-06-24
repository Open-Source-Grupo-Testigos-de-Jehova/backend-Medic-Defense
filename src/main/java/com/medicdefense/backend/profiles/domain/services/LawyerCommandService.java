package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.domain.model.commands.*;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

import java.util.Optional;

public interface LawyerCommandService {
    Optional<MedicDefenseRecordId> handle(CreateLawyerCommand command);
    Optional<Lawyer> handle(UpdatePriceCommand command);
    Optional<Lawyer> handle(UpdateWonCasesCommand command);
    Optional<Lawyer> handle(UpdateYearExperienceCommand command);
}
