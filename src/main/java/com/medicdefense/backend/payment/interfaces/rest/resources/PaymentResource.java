package com.medicdefense.backend.payment.interfaces.rest.resources;


import java.util.Date;

public record PaymentResource(Long id, Date createdAt,float amount, String method, Long consultationId) {
}