package com.medicdefense.backend.payment.interfaces.rest.transform;


import com.medicdefense.backend.payment.domain.model.commands.CreatePaymentCommand;
import com.medicdefense.backend.payment.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {

    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(
                resource.date(),
                resource.legalIssue(),
                resource.amount(),
                resource.method(),
                resource.consultationId()
        );
    }
}
