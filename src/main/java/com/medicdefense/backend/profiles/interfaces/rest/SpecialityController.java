package com.medicdefense.backend.profiles.interfaces.rest;

import com.medicdefense.backend.profiles.domain.model.queries.GetAllSpecialitiesQuery;
import com.medicdefense.backend.profiles.domain.services.SpecialityItemsQueryService;
import com.medicdefense.backend.profiles.interfaces.rest.resources.SpecialityItemResource;
import com.medicdefense.backend.profiles.interfaces.rest.transform.SpecialityItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/speciality", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Speciality", description = "Speciality Endpoints")
public class SpecialityController {
    private final SpecialityItemsQueryService specialityItemsQueryService;

    public SpecialityController(SpecialityItemsQueryService specialityItemsQueryService) {
        this.specialityItemsQueryService = specialityItemsQueryService;
    }

    @GetMapping
    public ResponseEntity<List<SpecialityItemResource>> getAllSpecialityItems() {
        var specialityItems = specialityItemsQueryService.handle(new GetAllSpecialitiesQuery());
        var specialityItemResources = specialityItems.stream()
                .map(SpecialityItemResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(specialityItemResources);
    }
}
