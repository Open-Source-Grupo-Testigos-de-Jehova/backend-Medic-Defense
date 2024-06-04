package com.medicdefense.backend.consultation.domain.services;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.queries.GetAllLegalConsultationsQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByMedicDefenseRecordIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByLawyerIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByMedicIdQuery;

import java.util.List;

public interface ConsultationQueryService {
    List<LegalConsultation> getAllLegalConsultations(GetAllLegalConsultationsQuery query);
    LegalConsultation getLegalConsultationByMedicDefenseRecordId(GetLegalConsultationsByMedicDefenseRecordIdQuery query);
    List<LegalConsultation> getLegalConsultationsByLawyerId(GetLegalConsultationsByLawyerIdQuery query);
    List<LegalConsultation> getLegalConsultationsByMedicId(GetLegalConsultationsByMedicIdQuery query);
}
