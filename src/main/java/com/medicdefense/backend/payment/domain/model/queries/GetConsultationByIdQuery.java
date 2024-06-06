package com.medicdefense.backend.payment.domain.model.queries;

/**
 * Query to retrieve a Consultation by its identifier.
 *
 * @param id The identifier of the Consultation.
 * @throws IllegalArgumentException if the identifier is less than or equal to 0.
 */
public record GetConsultationByIdQuery(int id) {
    public GetConsultationByIdQuery {
        if (id <= 0) {
            throw new IllegalArgumentException("The identifier must be greater than 0");
        }
    }
}
