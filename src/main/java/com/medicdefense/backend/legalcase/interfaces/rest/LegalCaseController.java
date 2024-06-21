package com.medicdefense.backend.legalcase.interfaces.rest;

import com.medicdefense.backend.legalcase.application.internal.commandservices.LegalCaseCommandServiceImpl;
import com.medicdefense.backend.legalcase.application.internal.queryservices.LegalCaseQueryServiceImpl;
import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import com.medicdefense.backend.legalcase.domain.model.commands.CloseLegalCaseCommand;
import com.medicdefense.backend.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.medicdefense.backend.legalcase.domain.model.queries.GetAllLegalCasesQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByDescriptionQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByIdQuery;
import com.medicdefense.backend.legalcase.domain.model.queries.GetLegalCaseByStatusQuery;
import com.medicdefense.backend.legalcase.domain.model.valueobjects.LegalCaseStatus;
import com.medicdefense.backend.legalcase.interfaces.rest.resources.CreateLegalCaseResource;
import com.medicdefense.backend.legalcase.interfaces.rest.resources.LegalCaseResource;
import com.medicdefense.backend.legalcase.interfaces.rest.transform.CreateLegalCaseCommandFromResourceAssembler;
import com.medicdefense.backend.legalcase.interfaces.rest.transform.LegalCaseResourceFromEntityAssembler;
import com.medicdefense.backend.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/legalcases", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Legal Cases", description = "Legal Cases Endpoints")
public class LegalCaseController {
    private final LegalCaseCommandServiceImpl commandService;
    private final LegalCaseQueryServiceImpl queryService;

    public LegalCaseController(LegalCaseCommandServiceImpl commandService, LegalCaseQueryServiceImpl queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping("{legalCaseId}/close")
    public ResponseEntity<MessageResource> closeLegalCase(@PathVariable Long legalCaseId) {
        CloseLegalCaseCommand command = new CloseLegalCaseCommand(legalCaseId);
        commandService.handle(command);
        return ResponseEntity.ok(new MessageResource("Closed Legal Case ID: " + legalCaseId));
    }

    @PostMapping
    public ResponseEntity<LegalCaseResource> createLegalCase(@RequestBody CreateLegalCaseResource resource) {
        CreateLegalCaseCommand command = CreateLegalCaseCommandFromResourceAssembler.toCommandFromResource(resource);
        LegalCase legalCase = commandService.handle(command).orElseThrow();
        LegalCaseResource legalCaseResource = LegalCaseResourceFromEntityAssembler.toResourceFromEntity(legalCase);
        return new ResponseEntity<>(legalCaseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{legalCaseId}")
    public ResponseEntity<LegalCaseResource> getLegalCase(@PathVariable Long legalCaseId) {
        LegalCase legalCase = queryService.handle(new GetLegalCaseByIdQuery(legalCaseId)).orElseThrow();
        LegalCaseResource legalCaseResource = LegalCaseResourceFromEntityAssembler.toResourceFromEntity(legalCase);
        return ResponseEntity.ok(legalCaseResource);
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<LegalCaseResource>> getLegalCasesByStatus(@PathVariable String status) {
        var legalCaseStatus = LegalCaseStatus.valueOf(status);
        if(legalCaseStatus != LegalCaseStatus.CLOSED && legalCaseStatus != LegalCaseStatus.OPEN)
            return ResponseEntity.badRequest().build();
        List<LegalCase> legalCases = queryService.handle(new GetLegalCaseByStatusQuery(legalCaseStatus));
        List<LegalCaseResource> legalCaseResources = legalCases.stream()
                .map(LegalCaseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(legalCaseResources);
    }
    @GetMapping
    public ResponseEntity<List<LegalCaseResource>> getAllLegalCases() {
        List<LegalCase> legalCases = queryService.handle(new GetAllLegalCasesQuery());
        List<LegalCaseResource> legalCaseResources = legalCases.stream()
                .map(LegalCaseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(legalCaseResources);
    }
}