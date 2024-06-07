package com.medicdefense.backend.payment.domain.model.queries;

/**
 * Query to retrieve a Payment by its identifier.
 *
 * @param id The identifier of the Payment.
 * @throws IllegalArgumentException if the identifier is less than or equal to 0.
 */
public record GetPaymentByIdQuery(Long id) {
    public GetPaymentByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
    }
}
