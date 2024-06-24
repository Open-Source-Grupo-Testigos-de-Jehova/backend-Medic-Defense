package com.medicdefense.backend.profiles.interfaces.rest;

import com.medicdefense.backend.profiles.domain.model.queries.*;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.UserId;
import com.medicdefense.backend.profiles.domain.services.MedicStudentCommandService;
import com.medicdefense.backend.profiles.domain.services.MedicStudentQueryService;
import com.medicdefense.backend.profiles.interfaces.rest.resources.CreateMedicStudentResource;
import com.medicdefense.backend.profiles.interfaces.rest.resources.MedicStudentResource;
import com.medicdefense.backend.profiles.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/medic_student", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Medic Student", description = "Medic Student Endpoints")
public class MedicStudentController {
    private final MedicStudentCommandService medicStudentCommandService;
    private final MedicStudentQueryService medicStudentQueryService;

    public MedicStudentController(MedicStudentCommandService medicStudentCommandService, MedicStudentQueryService medicStudentQueryService) {
        this.medicStudentCommandService = medicStudentCommandService;
        this.medicStudentQueryService = medicStudentQueryService;
    }



    @PostMapping
    public ResponseEntity<MedicStudentResource> createMedicStudent(@RequestBody CreateMedicStudentResource resource)
    {
        var createMedicStudentCommand = CreateMedicStudentCommandFromResourceAssembler.toCommandFromResource(resource);
        var medicStudentId = medicStudentCommandService.handle(createMedicStudentCommand);
        if(medicStudentId.RecordId().isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var getMedicStudentByMedicDefenseRecordIdQuery = new GetMedicStudentByMedicDefenseRecordIdQuery(medicStudentId);
        var medicStudent = medicStudentQueryService.handle(getMedicStudentByMedicDefenseRecordIdQuery);
        if(medicStudent.isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var medicStudentResource = MedicStudentResourceFromEntityAssembler.toResourceFromEntity(medicStudent.get());
        return new ResponseEntity<>(medicStudentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{recordId}/medic-defense-record")
    public ResponseEntity<MedicStudentResource> getMedicStudentByRecordId(@PathVariable String recordId)
    {
        var medicDefenseRecordId = new MedicDefenseRecordId(recordId);
        var getMedicStudentByMedicDefenseRecordIdQuery = new GetMedicStudentByMedicDefenseRecordIdQuery(medicDefenseRecordId);
        var medicStudent = medicStudentQueryService.handle(getMedicStudentByMedicDefenseRecordIdQuery);
        if(medicStudent.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var medicStudentResource = MedicStudentResourceFromEntityAssembler.toResourceFromEntity(medicStudent.get());
        return ResponseEntity.ok(medicStudentResource);
    }

    @GetMapping("/{userId}/user")
    public ResponseEntity<MedicStudentResource> getMedicByUserId(@PathVariable Long userId)
    {
        var getMedicByUserIdQuery = new GetMedicStudentByUserIdQuery(new UserId(userId));
        var medicStudent = medicStudentQueryService.handle(getMedicByUserIdQuery);
        if(medicStudent.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var medicResource = MedicStudentResourceFromEntityAssembler.toResourceFromEntity(medicStudent.get());
        return ResponseEntity.ok(medicResource);
    }

    @GetMapping
    public ResponseEntity<List<MedicStudentResource>> getAllMedicStudents() {
        var getAllMedicStudentsQuery = new GetAllMedicStudentsQuery();
        var medicStudents = medicStudentQueryService.handle(getAllMedicStudentsQuery);
        var medicStudentResources = medicStudents.stream()
                .map(MedicStudentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(medicStudentResources);
    }

    @PutMapping("/{recordId}/add-one-to-consultation")
    public ResponseEntity<MedicStudentResource> addOneToConsultation(@PathVariable String recordId) {
        var medicDefenseRecordId = new MedicDefenseRecordId(recordId);
        var addOneToConsultationCommand = AddOneToConsultationMedicStudentCommandFromResourceAssembler.ToCommandFromResource(medicDefenseRecordId);
        var medicStudent = medicStudentCommandService.handle(addOneToConsultationCommand);
        if(medicStudent.isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var medicStudentResource = MedicStudentResourceFromEntityAssembler.toResourceFromEntity(medicStudent.get());
        return ResponseEntity.ok(medicStudentResource);
    }

    @PutMapping("/{recordId}/add-one-to-paid-service")
    public ResponseEntity<MedicStudentResource> addOneToPaidService(@PathVariable String recordId) {
        var medicDefenseRecordId = new MedicDefenseRecordId(recordId);
        var addOneToPaidServiceCommand = AddOneToPaidServiceMedicStudentCommandFromResourceAssembler.ToCommandFromResource(medicDefenseRecordId);
        var medicStudent = medicStudentCommandService.handle(addOneToPaidServiceCommand);
        if(medicStudent.isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var medicStudentResource = MedicStudentResourceFromEntityAssembler.toResourceFromEntity(medicStudent.get());
        return ResponseEntity.ok(medicStudentResource);
    }
}
