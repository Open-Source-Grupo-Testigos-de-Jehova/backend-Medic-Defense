package com.medicdefense.backend.consultation.application.internal.queryservices;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.queries.GetAllLegalConsultationsQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByLawyerIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByMedicIdQuery;
import com.medicdefense.backend.consultation.domain.services.LegalConsultationQueryService;
import com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories.LegalConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalConsultationQueryServiceImpl implements LegalConsultationQueryService {

    private final LegalConsultationRepository legalConsultationRepository;

    public LegalConsultationQueryServiceImpl(LegalConsultationRepository legalConsultationRepository) {
        this.legalConsultationRepository = legalConsultationRepository;
    }

    @Override
    public List<LegalConsultation> handle(GetAllLegalConsultationsQuery query) {
        return legalConsultationRepository.findAll();
    }

    @Override
    public Optional<LegalConsultation> handle(GetLegalConsultationsByIdQuery query) {
        return legalConsultationRepository.findById(query.consultationRecordId());
    }

    @Override
    public List<LegalConsultation> handle(GetLegalConsultationsByLawyerIdQuery query) {
        return legalConsultationRepository.findAllByLawyerId(query.lawyerId());
    }

    @Override
    public List<LegalConsultation> handle(GetLegalConsultationsByMedicIdQuery query) {
        return legalConsultationRepository.findAllByMedicId(query.medicId());
    }
}
