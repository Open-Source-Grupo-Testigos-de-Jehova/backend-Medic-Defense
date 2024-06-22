package com.medicdefense.backend.consultation.interfaces.rest;


import com.medicdefense.backend.consultation.domain.model.queries.GetAllLegalIssuesQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalIssueByIdQuery;
import com.medicdefense.backend.consultation.domain.services.LegalIssueQueryService;
import com.medicdefense.backend.consultation.interfaces.rest.resources.LegalIssueResource;
import com.medicdefense.backend.consultation.interfaces.rest.transform.LegalIssueResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/LegalIssue", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Legal Issues", description = "Legal Issue Management Endpoints")
public class LegalIssueController {
    private final LegalIssueQueryService legalIssueQueryService;

    public LegalIssueController(LegalIssueQueryService legalIssueQueryService) {
        this.legalIssueQueryService = legalIssueQueryService;
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
