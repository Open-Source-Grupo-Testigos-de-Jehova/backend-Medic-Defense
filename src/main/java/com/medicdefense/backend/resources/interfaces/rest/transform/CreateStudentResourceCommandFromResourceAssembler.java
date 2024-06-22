package com.medicdefense.backend.resources.interfaces.rest.transform;

import com.medicdefense.backend.resources.domain.model.commands.CreateStudentResourceCommand;
import com.medicdefense.backend.resources.interfaces.rest.resources.CreateStudentResourceResource;

public class CreateStudentResourceCommandFromResourceAssembler {
    public static CreateStudentResourceCommand toCommand(CreateStudentResourceResource resource) {
        return new CreateStudentResourceCommand(
                resource.studentRecordId(),
                resource.educationalResource()
        );
    }
}
