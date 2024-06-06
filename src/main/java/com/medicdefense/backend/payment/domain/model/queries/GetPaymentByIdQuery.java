package com.medicdefense.backend.payment.domain.model.queries;

/**
 * Query to retrieve a Payment by its identifier.
 *
 * @param id The identifier of the Payment.
 * @throws IllegalArgumentException if the identifier is less than or equal to 0.
 */
public record GetPaymentByIdQuery(int id) {
    public GetPaymentByIdQuery {
        if (id <= 0) {
            throw new IllegalArgumentException("The identifier must be greater than 0");
        }
    }

    public Integer paymentId() {
        return id;
    }
}
