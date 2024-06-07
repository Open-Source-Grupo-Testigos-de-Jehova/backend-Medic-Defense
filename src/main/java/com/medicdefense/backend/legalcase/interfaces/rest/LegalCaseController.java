package com.medicdefense.backend.legalcase.interfaces.rest;

import com.medicdefense.backend.legalcase.application.internal.commandservices.LegalCaseCommandServiceImpl;
import com.medicdefense.backend.legalcase.application.internal.queryservices.LegalCaseQueryServiceImpl;
import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import com.medicdefense.backend.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.medicdefense.backend.legalcase.domain.model.queries.GetAllLegalCasesQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByDescriptionQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByIdQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByStatusQuery;
import com.medicdefense.backend.legalcase.interfaces.rest.resources.CreateLegalCaseResource;
import com.medicdefense.backend.legalcase.interfaces.rest.resources.LegalCaseResource;
import com.medicdefense.backend.legalcase.interfaces.rest.transform.CreateLegalCaseCommandFromResourceAssembler;
import com.medicdefense.backend.legalcase.interfaces.rest.transform.LegalCaseResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/legalcases")
public class LegalCaseController {
    private final LegalCaseCommandServiceImpl commandService;
    private final LegalCaseQueryServiceImpl queryService;

    public LegalCaseController(LegalCaseCommandServiceImpl commandService, LegalCaseQueryServiceImpl queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<LegalCaseResource> createLegalCase(@RequestBody CreateLegalCaseResource resource) {
        CreateLegalCaseCommand command = CreateLegalCaseCommandFromResourceAssembler.toCommandFromResource(resource);
        LegalCase legalCase = commandService.handle(command).orElseThrow();
        LegalCaseResource legalCaseResource = LegalCaseResourceFromEntityAssembler.toResourceFromEntity(legalCase);
        return new ResponseEntity<>(legalCaseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LegalCaseResource> getLegalCase(@PathVariable Long id) {
        LegalCase legalCase = queryService.handle(new GetLegalCaseByIdQuery(id)).orElseThrow();
        LegalCaseResource legalCaseResource = LegalCaseResourceFromEntityAssembler.toResourceFromEntity(legalCase);
        return ResponseEntity.ok(legalCaseResource);
    }

    @GetMapping("/description/{description}")
    public ResponseEntity<List<LegalCaseResource>> getLegalCasesByDescription(@PathVariable String description) {
        List<LegalCase> legalCases = queryService.handle(new GetLegalCaseByDescriptionQuery(description));
        List<LegalCaseResource> legalCaseResources = legalCases.stream()
                .map(LegalCaseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(legalCaseResources);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LegalCaseResource>> getLegalCasesByStatus(@PathVariable String status) {
        List<LegalCase> legalCases = queryService.handle(new GetLegalCaseByStatusQuery(status));
        List<LegalCaseResource> legalCaseResources = legalCases.stream()
                .map(LegalCaseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(legalCaseResources);
    }
    @GetMapping("/all")
    public ResponseEntity<List<LegalCaseResource>> getAllLegalCases() {
        List<LegalCase> legalCases = queryService.handle(new GetAllLegalCasesQuery());
        List<LegalCaseResource> legalCaseResources = legalCases.stream()
                .map(LegalCaseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(legalCaseResources);
    }
}