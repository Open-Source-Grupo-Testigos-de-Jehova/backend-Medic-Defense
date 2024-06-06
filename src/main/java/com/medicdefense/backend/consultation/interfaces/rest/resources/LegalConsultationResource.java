package com.medicdefense.backend.consultation.interfaces.rest.resources;

import java.sql.Date;

public record LegalConsultationResource(Long legalConsultationId, Long medicId, Long lawyerId, Date lastConsultation) {
}
