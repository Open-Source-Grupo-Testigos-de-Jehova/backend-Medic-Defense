package com.medicdefense.backend.profiles.interfaces.acl;

import com.medicdefense.backend.profiles.domain.model.commands.CreateMedicCommand;
import com.medicdefense.backend.profiles.domain.services.MedicCommandService;
import com.medicdefense.backend.profiles.domain.services.MedicQueryService;
import org.springframework.stereotype.Service;

@Service
public class MedicContextFacade {
    private final MedicCommandService medicCommandService;
    private final MedicQueryService medicQueryService;

    public MedicContextFacade(MedicCommandService medicCommandService, MedicQueryService medicQueryService) {
        this.medicCommandService = medicCommandService;
        this.medicQueryService = medicQueryService;
    }

    public String createMedic(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String DNI,
            String image_url
    ) {
        var createMedicCommand = new CreateMedicCommand(firstName, lastName, email, phoneNumber, DNI, image_url);
        var medic = medicCommandService.handle(createMedicCommand);
        if (medic.isEmpty()) return null;
        return medic.get().RecordId();
    }
}
