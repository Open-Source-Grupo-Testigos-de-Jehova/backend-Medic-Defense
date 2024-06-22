package com.medicdefense.backend.resources.interfaces.rest.transform;

import com.medicdefense.backend.resources.domain.model.entities.StudentResource;
import com.medicdefense.backend.resources.interfaces.rest.resources.StudentResourceResource;

public class StudentResourceResourceFromEntityAssembler {
    public static StudentResourceResource toResource(StudentResource entity) {
        return new StudentResourceResource(
                entity.getId(),
                entity.getStudentRecordId(),
                entity.getEducationalResource()
        );
    }
}
