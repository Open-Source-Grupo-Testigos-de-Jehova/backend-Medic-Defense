package com.medicdefense.backend.iam.application.internal.outboundservices.acl;

import com.medicdefense.backend.iam.domain.model.valueobjects.RecordId;
import com.medicdefense.backend.profiles.interfaces.acl.MedicContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalMedicService {
    private final MedicContextFacade medicContextFacade;

    public ExternalMedicService(MedicContextFacade medicContextFacade) {
        this.medicContextFacade = medicContextFacade;
    }

    public Optional<RecordId> createMedic(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String DNI,
            String image_url
    ) {
        var medicId = medicContextFacade.createMedic(firstName, lastName, email, phoneNumber, DNI, image_url);
        if (medicId == null) return Optional.empty();
        return Optional.of(new RecordId(medicId));
    }
}
