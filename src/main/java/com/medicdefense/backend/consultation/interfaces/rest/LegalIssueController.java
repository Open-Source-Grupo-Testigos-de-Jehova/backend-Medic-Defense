package com.medicdefense.backend.consultation.interfaces.rest;


import com.medicdefense.backend.consultation.domain.model.queries.GetAllLegalIssuesQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalIssueByIdQuery;
import com.medicdefense.backend.consultation.domain.services.LegalIssueCommandService;
import com.medicdefense.backend.consultation.domain.services.LegalIssueQueryService;
import com.medicdefense.backend.consultation.interfaces.rest.resources.AskLegalIssueResource;
import com.medicdefense.backend.consultation.interfaces.rest.resources.LegalIssueResource;
import com.medicdefense.backend.consultation.interfaces.rest.transform.AskLegalIssueCommandFromResourceAssembler;
import com.medicdefense.backend.consultation.interfaces.rest.transform.LegalIssueResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/LegalIssue", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Legal Issues", description = "Legal Issue Management Endpoints")
public class LegalIssueController {
    private final LegalIssueCommandService legalIssueCommandService;
    private final LegalIssueQueryService legalIssueQueryService;

    public LegalIssueController(LegalIssueCommandService legalIssueCommandService, LegalIssueQueryService legalIssueQueryService) {
        this.legalIssueCommandService = legalIssueCommandService;
        this.legalIssueQueryService = legalIssueQueryService;
    }

    @PostMapping
    public ResponseEntity<LegalIssueResource> createLegalIssue(@RequestBody AskLegalIssueResource askLegalIssueResource) {
        var createLegalIssueCommand = AskLegalIssueCommandFromResourceAssembler.toCommandFromResource(askLegalIssueResource);
        var legalIssueId = legalIssueCommandService.handle(createLegalIssueCommand);
        if(legalIssueId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getLegalIssueByIdQuery = new GetLegalIssueByIdQuery(legalIssueId);
        var legalIssue = legalIssueQueryService.handle(getLegalIssueByIdQuery);
        if(legalIssue.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var legalIssueResource = LegalIssueResourceFromEntityAssembler.toResourceFromEntity(legalIssue.get());
        return new ResponseEntity<>(legalIssueResource, HttpStatus.CREATED);
    }

    @GetMapping("/{legalIssueId}")
    public ResponseEntity<LegalIssueResource> getLegalIssueById(@PathVariable Long legalIssueId) {
            var getLegalIssueByIdQuery = new GetLegalIssueByIdQuery(legalIssueId);
            var legalIssue = legalIssueQueryService.handle(getLegalIssueByIdQuery);
            if(legalIssue.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            var legalIssueResource = LegalIssueResourceFromEntityAssembler.toResourceFromEntity(legalIssue.get());
            return ResponseEntity.ok(legalIssueResource);
    }

    @GetMapping
    public ResponseEntity<List<LegalIssueResource>> getAllLegalIssues() {
        var getAllLegalIssuesQuery = new GetAllLegalIssuesQuery();
        var legalIssues = legalIssueQueryService.handle(getAllLegalIssuesQuery);
        var legalIssueResource = legalIssues.stream().map(LegalIssueResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(legalIssueResource);
    }
}
