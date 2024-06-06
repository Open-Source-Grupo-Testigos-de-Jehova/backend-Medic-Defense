package com.medicdefense.backend.payment.domain.model.commands;

import java.util.Date;

/**
 * Command to create a Consultation.
 *
 * <p>The CreateConsultationCommand encapsulates the necessary data to create a Consultation.
 * It validates that the date and the legal issue are not null or empty.</p>
 *
 * @param date The date of the consultation.
 * @param legalIssue The legal issue of the consultation.
 * @throws IllegalArgumentException if the date or the legal issue is null or empty.
 */
public record CreateConsultationCommand(Date date, String legalIssue) {
    public CreateConsultationCommand {
        if (date == null) {
            throw new IllegalArgumentException("The date cannot be null");
        }
        if (legalIssue == null || legalIssue.isBlank()) {
            throw new IllegalArgumentException("The legal issue cannot be null or empty");
        }
    }
}
