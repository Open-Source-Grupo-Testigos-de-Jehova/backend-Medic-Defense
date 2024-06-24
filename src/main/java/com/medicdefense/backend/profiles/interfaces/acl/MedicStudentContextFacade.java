package com.medicdefense.backend.profiles.interfaces.acl;

import com.medicdefense.backend.profiles.domain.model.commands.CreateMedicStudentCommand;
import com.medicdefense.backend.profiles.domain.services.MedicStudentCommandService;
import com.medicdefense.backend.profiles.domain.services.MedicStudentQueryService;
import org.springframework.stereotype.Service;

@Service
public class MedicStudentContextFacade {
    private final MedicStudentCommandService medicStudentCommandService;

    public MedicStudentContextFacade(MedicStudentCommandService medicStudentCommandService) {
        this.medicStudentCommandService = medicStudentCommandService;
    }

    public String createMedicStudent(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String DNI,
            String image_url,
            String university
    ) {
        var createMedicStudentCommand = new CreateMedicStudentCommand(firstName, lastName, email, phoneNumber, DNI, image_url, university);
        var medicStudent = medicStudentCommandService.handle(createMedicStudentCommand);
        if (medicStudent.isEmpty()) return null;
        return medicStudent.get().RecordId();
    }
}
