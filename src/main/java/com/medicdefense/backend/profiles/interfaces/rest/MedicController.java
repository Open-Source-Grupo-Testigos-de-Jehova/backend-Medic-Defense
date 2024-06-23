package com.medicdefense.backend.profiles.interfaces.rest;

import com.medicdefense.backend.profiles.domain.model.queries.GetAllMedicsQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetMedicByMedicDefenseRecordIdQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetMedicByProfileIdQuery;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.profiles.domain.services.MedicCommandService;
import com.medicdefense.backend.profiles.domain.services.MedicQueryService;
import com.medicdefense.backend.profiles.interfaces.rest.resources.CreateMedicResource;
import com.medicdefense.backend.profiles.interfaces.rest.resources.MedicResource;
import com.medicdefense.backend.profiles.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/medic", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Medic", description = "Medic Endpoints")
public class MedicController {
    private final MedicCommandService medicCommandService;
    private final MedicQueryService medicQueryService;

    public MedicController(MedicCommandService medicCommandService, MedicQueryService medicQueryService) {
        this.medicCommandService = medicCommandService;
        this.medicQueryService = medicQueryService;
    }


    @PostMapping
    public ResponseEntity<MedicResource> createMedic(@RequestBody CreateMedicResource resource)
    {
        var createMedicCommand = CreateMedicCommandFromResourceAssembler.toCommandFromResource(resource);
        var medicId = medicCommandService.handle(createMedicCommand);
        if(medicId.RecordId().isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var getMedicByRecordIdQuery = new GetMedicByMedicDefenseRecordIdQuery(medicId);
        var medic = medicQueryService.handle(getMedicByRecordIdQuery);
        if(medic.isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var medicResource = MedicResourceFromEntityAssembler.toResourceFromEntity(medic.get());
        return new ResponseEntity<>(medicResource, HttpStatus.CREATED);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<MedicResource> getMedicByRecordId(@PathVariable String recordId)
    {
        var medicDefenseRecordId = new MedicDefenseRecordId(recordId);
        var getMedicByRecordIdQuery = new GetMedicByMedicDefenseRecordIdQuery(medicDefenseRecordId);
        var medic = medicQueryService.handle(getMedicByRecordIdQuery);
        if(medic.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var medicResource = MedicResourceFromEntityAssembler.toResourceFromEntity(medic.get());
        return ResponseEntity.ok(medicResource);
    }

    @GetMapping
    public ResponseEntity<List<MedicResource>> getAllMedics()
    {
        var getAllMedicsQuery = new GetAllMedicsQuery();
        var medics = medicQueryService.handle(getAllMedicsQuery);
        var medicResources = medics
                .stream()
                .map(MedicResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(medicResources);
    }

    @GetMapping("/profileId")
    public ResponseEntity<MedicResource> getMedicByProfileId(@PathVariable Long profileId)
    {
        var Id = new ProfileId(profileId);
        var getMedicByProfileIdQuery = new GetMedicByProfileIdQuery(Id);
        var medic = medicQueryService.handle(getMedicByProfileIdQuery);
        if(medic.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var medicResource = MedicResourceFromEntityAssembler.toResourceFromEntity(medic.get());
        return ResponseEntity.ok(medicResource);
    }

    @PutMapping("/{recordId}/add-one-to-consultation")
    public ResponseEntity<MedicResource> addOneToConsultation(@PathVariable String recordId)
    {
        var medicDefenseRecordId = new MedicDefenseRecordId(recordId);
        var addOneToConsultationCommand = AddOneToConsultationMedicCommandFromResourceAssembler.ToCommandFromResource(medicDefenseRecordId);
        var addOneToConsultation = medicCommandService.handle(addOneToConsultationCommand);
        if(addOneToConsultation.isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var medicResource = MedicResourceFromEntityAssembler.toResourceFromEntity(addOneToConsultation.get());
        return ResponseEntity.ok(medicResource);
    }

    @PutMapping("/{recordId}/add-one-to-paid-service")
    public ResponseEntity<MedicResource> addOneToPaidService(@PathVariable String recordId)
    {
        var medicDefenseRecordId = new MedicDefenseRecordId(recordId);
        var addOneToPaidServiceCommand = AddOneToPaidServiceMedicCommandFromResourceAssembler.ToCommandFromResource(medicDefenseRecordId);
        var addOneToPaidService = medicCommandService.handle(addOneToPaidServiceCommand);
        if(addOneToPaidService.isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var medicResource = MedicResourceFromEntityAssembler.toResourceFromEntity(addOneToPaidService.get());
        return ResponseEntity.ok(medicResource);
    }
}

