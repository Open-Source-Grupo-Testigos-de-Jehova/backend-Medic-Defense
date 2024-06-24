package com.medicdefense.backend.iam.application.internal.outboundservices.acl;

import com.medicdefense.backend.iam.domain.model.valueobjects.RecordId;
import com.medicdefense.backend.profiles.interfaces.acl.MedicStudentContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalMedicStudentService {
    private final MedicStudentContextFacade medicStudentContextFacade;

    public ExternalMedicStudentService(MedicStudentContextFacade medicStudentContextFacade) {
        this.medicStudentContextFacade = medicStudentContextFacade;
    }

    public Optional<RecordId> createMedicStudent(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String DNI,
            String image_url,
            String university
    ) {
        var medicStudentId = medicStudentContextFacade.createMedicStudent(firstName, lastName, email, phoneNumber, DNI, image_url, university);
        if (medicStudentId == null) return Optional.empty();
        return Optional.of(new RecordId(medicStudentId));
    }
}
