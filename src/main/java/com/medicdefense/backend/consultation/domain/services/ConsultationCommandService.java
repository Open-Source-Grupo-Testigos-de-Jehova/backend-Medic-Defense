package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.aggregate.Consultation;
import com.medicdefense.backend.consultation.domain.model.commands.CreateConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.commands.DeleteConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.commands.UpdateConsultationCommand;
import org.hibernate.sql.Update;

import java.util.Optional;

public interface ConsultationCommandService {
    Long handle(CreateConsultationCommand command);
    Optional<Consultation> handle(UpdateConsultationCommand command);
    void handle(DeleteConsultationCommand command);
}
