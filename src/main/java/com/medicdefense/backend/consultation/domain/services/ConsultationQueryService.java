package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.aggregate.Consultation;
import com.medicdefense.backend.consultation.domain.model.queries.GetAllConsultationsQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetConsultationsByMedicDefenseRecordIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetConsultationsByLawyerIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetConsultationsByMedicIdQuery;

import java.util.List;

public interface ConsultationQueryService {
    List<Consultation> getAllConsultations(GetAllConsultationsQuery query);
    Consultation getConsultationById(GetConsultationsByMedicDefenseRecordIdQuery query);
    List<Consultation> getConsultationsByLawyerId(GetConsultationsByLawyerIdQuery query);
    List<Consultation> getConsultationsByMedicId(GetConsultationsByMedicIdQuery query);
}
