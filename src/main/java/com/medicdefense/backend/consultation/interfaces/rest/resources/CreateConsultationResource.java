package com.medicdefense.backend.consultation.interfaces.rest.resources;

import java.sql.Date;

public record CreateConsultationResource(Date date, Long medicId, Long lawyerId) {
}
