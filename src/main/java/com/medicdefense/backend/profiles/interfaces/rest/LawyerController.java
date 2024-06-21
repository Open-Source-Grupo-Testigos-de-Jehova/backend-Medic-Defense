package com.medicdefense.backend.profiles.interfaces.rest;


import com.medicdefense.backend.profiles.domain.model.queries.GetAllLawyersQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetLawyerByMedicDefenseRecordIdQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetLawyerByProfileIdQuery;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.profiles.domain.services.LawyerCommandService;
import com.medicdefense.backend.profiles.domain.services.LawyerQueryService;
import com.medicdefense.backend.profiles.interfaces.rest.resources.CreateLawyerResource;
import com.medicdefense.backend.profiles.interfaces.rest.resources.LawyerResource;
import com.medicdefense.backend.profiles.interfaces.rest.transform.CreateLawyerCommandFromResourceAssembler;
import com.medicdefense.backend.profiles.interfaces.rest.transform.LawyerResourceFromEntityAssembler;
import com.medicdefense.backend.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/lawyers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Lawyer", description = "Lawyer Endpoints")
public class LawyerController {
    private final LawyerCommandService lawyerCommandService;
    private final LawyerQueryService lawyerQueryService;

    public LawyerController(LawyerCommandService lawyerCommandService, LawyerQueryService lawyerQueryService) {
        this.lawyerCommandService = lawyerCommandService;
        this.lawyerQueryService = lawyerQueryService;
    }

    @PostMapping
    public ResponseEntity<LawyerResource> createLawyer(@RequestBody CreateLawyerResource resource)
    {
        var createLawyerCommand = CreateLawyerCommandFromResourceAssembler.toCommandFromResource(resource);
        var lawyerId = lawyerCommandService.handle(createLawyerCommand);
        if(lawyerId.RecordId().isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var getLawyerByRecordIdQuery = new GetLawyerByMedicDefenseRecordIdQuery(lawyerId);
        var lawyer = lawyerQueryService.handle(getLawyerByRecordIdQuery);
        if(lawyer.isEmpty())
        {
            return ResponseEntity.badRequest().build();
        }
        var lawyerResource = LawyerResourceFromEntityAssembler.toResourceFromEntity(lawyer.get());
        return new ResponseEntity<>(lawyerResource, HttpStatus.CREATED);
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<LawyerResource> getLawyerByRecordId(@PathVariable String recordId)
    {
        var medicDefenseRecordId = new MedicDefenseRecordId(recordId);
        var getLawyerByRecordIdQuery = new GetLawyerByMedicDefenseRecordIdQuery(medicDefenseRecordId);
        var lawyer = lawyerQueryService.handle(getLawyerByRecordIdQuery);
        if(lawyer.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var lawyerResource = LawyerResourceFromEntityAssembler.toResourceFromEntity(lawyer.get());
        return ResponseEntity.ok(lawyerResource);
    }

    @GetMapping
    public ResponseEntity<List<LawyerResource>> getAllLawyers()
    {
        var getAllLawyersQuery = new GetAllLawyersQuery();
        var lawyers = lawyerQueryService.handle(getAllLawyersQuery);
        if(lawyers.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var lawyerResources = lawyers.stream()
                .map(LawyerResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lawyerResources);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<LawyerResource> getLawyerByProfileId(@PathVariable long profileId)
    {
        var Id = new ProfileId(profileId);
        var getLawyerByProfileIdQuery = new GetLawyerByProfileIdQuery(Id);
        var lawyer = lawyerQueryService.handle(getLawyerByProfileIdQuery);
        if(lawyer.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var lawyerResource = LawyerResourceFromEntityAssembler.toResourceFromEntity(lawyer.get());
        return ResponseEntity.ok(lawyerResource);
    }
}
