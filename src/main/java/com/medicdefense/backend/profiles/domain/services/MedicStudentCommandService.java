package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.MedicStudent;
import com.medicdefense.backend.profiles.domain.model.commands.AddOneToConsultationMedicStudentMadeCommand;
import com.medicdefense.backend.profiles.domain.model.commands.AddOneToPaidServiceMedicStudentCommand;
import com.medicdefense.backend.profiles.domain.model.commands.AddUniversityCommand;
import com.medicdefense.backend.profiles.domain.model.commands.CreateMedicStudentCommand;
import com.medicdefense.backend.profiles.domain.model.entities.University;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;

import java.util.Optional;

public interface MedicStudentCommandService {
    MedicDefenseRecordId handle(CreateMedicStudentCommand command);
    void handle(AddUniversityCommand command);
    Optional<MedicStudent> handle(AddOneToConsultationMedicStudentMadeCommand command);
    Optional<MedicStudent> handle(AddOneToPaidServiceMedicStudentCommand command);
}
