package com.medicdefense.backend.resources.domain.model.entities;

import com.medicdefense.backend.resources.domain.model.aggregates.EducationalResource;
import com.medicdefense.backend.resources.domain.model.commands.CreateStudentResourceCommand;
import com.medicdefense.backend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StudentResource extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentRecordId;

    @Column(name = "educational_resource_id", nullable = false)
    private Long educationalResource;

    protected StudentResource() {
    }

    public StudentResource(String studentRecordId, Long educationalResource) {
        this.studentRecordId = studentRecordId;
        this.educationalResource = educationalResource;
    }

    public StudentResource(CreateStudentResourceCommand command)
    {
        this.studentRecordId = command.studentRecordId();
        this.educationalResource = command.educationalResource();
    }
}

