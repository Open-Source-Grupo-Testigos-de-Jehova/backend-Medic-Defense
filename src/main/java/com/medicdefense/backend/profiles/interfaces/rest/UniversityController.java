package com.medicdefense.backend.profiles.interfaces.rest;

import com.medicdefense.backend.profiles.domain.model.queries.GetAllUniversitiesQuery;
import com.medicdefense.backend.profiles.domain.services.UniversityCommandService;
import com.medicdefense.backend.profiles.domain.services.UniversityQueryService;
import com.medicdefense.backend.profiles.interfaces.rest.resources.CreateUniversityResource;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UniversityResource;
import com.medicdefense.backend.profiles.interfaces.rest.transform.CreateUniversityCommandFromResourceAssembler;
import com.medicdefense.backend.profiles.interfaces.rest.transform.UniversityResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/universities", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "University", description = "University Endpoints")
public class UniversityController {
    private final UniversityCommandService universityCommandService;
    private final UniversityQueryService universityQueryService;

    public UniversityController(UniversityCommandService universityCommandService, UniversityQueryService universityQueryService) {
        this.universityCommandService = universityCommandService;
        this.universityQueryService = universityQueryService;
    }

    @PostMapping
    public ResponseEntity<UniversityResource> createUniversity(@RequestBody CreateUniversityResource resource) {
        var createUniversityCommand = CreateUniversityCommandFromResourceAssembler.toCommandFromResource(resource);
        var university = universityCommandService.handle(createUniversityCommand);
        if (university.isEmpty()) return ResponseEntity.badRequest().build();
        var universityResource = UniversityResourceFromEntityAssembler.toResourceFromEntity(university.get());
        return new ResponseEntity<>(universityResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UniversityResource>> getAllUniversities() {
        var universities = universityQueryService.handle(new GetAllUniversitiesQuery());
        var universityResources = universities.stream()
                .map(UniversityResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(universityResources);
    }
}
