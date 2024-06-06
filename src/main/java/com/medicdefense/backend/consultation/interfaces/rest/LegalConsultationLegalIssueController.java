package com.medicdefense.backend.consultation.interfaces.rest;

import com.medicdefense.backend.consultation.domain.model.queries.GetLegalIssuesByConsultationIdQuery;
import com.medicdefense.backend.consultation.domain.services.LegalIssueQueryService;
import com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories.LegalIssueRepository;
import com.medicdefense.backend.consultation.interfaces.rest.resources.LegalIssueResource;
import com.medicdefense.backend.consultation.interfaces.rest.transform.LegalIssueResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/legal-consultation/{legalConsultationId}/LegalIssue", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Legal Issues")
public class LegalConsultationLegalIssueController {

    public final LegalIssueQueryService legalIssueQueryService;

    public LegalConsultationLegalIssueController(LegalIssueQueryService legalIssueQueryService) {
        this.legalIssueQueryService = legalIssueQueryService;
    }

    @GetMapping
    public ResponseEntity<List<LegalIssueResource>> getAllLegalIssueByLegalConsultationId(@PathVariable Long legalConsultationId) {
        var getAllLegalIssuesByLegalConsultationIdQuery = new GetLegalIssuesByConsultationIdQuery(legalConsultationId);
        var legalIssues = legalIssueQueryService.handle(getAllLegalIssuesByLegalConsultationIdQuery);
        var legalIssueResources = legalIssues.stream().map(LegalIssueResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(legalIssueResources);
    }
}
