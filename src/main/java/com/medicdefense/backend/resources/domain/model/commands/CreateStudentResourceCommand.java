package com.medicdefense.backend.resources.domain.model.commands;

import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;

public record CreateStudentResourceCommand (String studentRecordId, Long educationalResource){
}
