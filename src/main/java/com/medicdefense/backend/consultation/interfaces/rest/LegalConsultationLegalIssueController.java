package com.medicdefense.backend.consultation.interfaces.rest;

import com.medicdefense.backend.consultation.domain.model.commands.AskLegalIssueCommand;
import com.medicdefense.backend.consultation.domain.model.commands.CloseLegalIssueCommand;
import com.medicdefense.backend.consultation.domain.model.commands.RespondLegalIssueCommand;
import com.medicdefense.backend.consultation.domain.model.entities.LegalIssueItem;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalIssueByIdAndConsultationIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalIssueByNameAndConsultationIdQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalIssuesByConsultationIdQuery;
import com.medicdefense.backend.consultation.domain.services.LegalConsultationCommandService;
import com.medicdefense.backend.consultation.domain.services.LegalConsultationQueryService;
import com.medicdefense.backend.consultation.domain.services.LegalIssueQueryService;
import com.medicdefense.backend.consultation.infrastructure.persistence.jpa.repositories.LegalIssueRepository;
import com.medicdefense.backend.consultation.interfaces.rest.resources.LegalIssueResource;
import com.medicdefense.backend.consultation.interfaces.rest.transform.LegalIssueResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/legal-consultation/{legalConsultationId}/LegalIssue", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Legal Issues")
public class LegalConsultationLegalIssueController {
    public final LegalConsultationCommandService legalConsultationCommandService;
    public final LegalConsultationQueryService legalConsultationQueryService;
    public final LegalIssueQueryService legalIssueQueryService;

    public LegalConsultationLegalIssueController(LegalConsultationCommandService legalConsultationCommandService, LegalConsultationQueryService legalConsultationQueryService, LegalIssueQueryService legalIssueQueryService) {
        this.legalConsultationCommandService = legalConsultationCommandService;
        this.legalConsultationQueryService = legalConsultationQueryService;
        this.legalIssueQueryService = legalIssueQueryService;
    }

    @PostMapping("/{issueName}")
    public ResponseEntity<LegalIssueResource> askLegalIssue(@PathVariable String issueName, @PathVariable Long legalConsultationId) {
        legalConsultationCommandService.handle(new AskLegalIssueCommand(issueName, legalConsultationId));
        var getLegalIssueByNameAndConsultationIdQuery = new GetLegalIssueByNameAndConsultationIdQuery(issueName, legalConsultationId);
        var legalIssueItem = legalConsultationQueryService.handle(getLegalIssueByNameAndConsultationIdQuery);
        if (legalIssueItem.isEmpty()) return ResponseEntity.notFound().build();
                else {
                    var legalIssueResource = LegalIssueResourceFromEntityAssembler.toResourceFromEntity(legalIssueItem.get());
                    return ResponseEntity.ok(legalIssueResource);
                }
    }

    @PutMapping("/{legalIssueId}/{respond}")
    public ResponseEntity<LegalIssueResource> respondLegalIssue(@PathVariable Long legalIssueId, @PathVariable String respond, @PathVariable Long legalConsultationId) {
        legalConsultationCommandService.handle(new RespondLegalIssueCommand(legalConsultationId, legalIssueId, respond));
        var getLegalIssueByIdAndConsultationIdQuery = new GetLegalIssueByIdAndConsultationIdQuery(legalIssueId, legalConsultationId);
        var legalIssueItem = legalConsultationQueryService.handle(getLegalIssueByIdAndConsultationIdQuery);
        if (legalIssueItem.isEmpty()) return ResponseEntity.notFound().build();
        else {
            var legalIssueResource = LegalIssueResourceFromEntityAssembler.toResourceFromEntity(legalIssueItem.get());
            return ResponseEntity.ok(legalIssueResource);
        }
    }

    @PutMapping("/{legalIssueId}/close")
    public ResponseEntity<LegalIssueResource> closeLegalIssue(@PathVariable Long legalIssueId, @PathVariable Long legalConsultationId) {
        legalConsultationCommandService.handle(new CloseLegalIssueCommand(legalConsultationId, legalIssueId));
        var getLegalIssueByIdAndConsultationIdQuery = new GetLegalIssueByIdAndConsultationIdQuery(legalIssueId, legalConsultationId);
        var legalIssueItem = legalConsultationQueryService.handle(getLegalIssueByIdAndConsultationIdQuery);
        if (legalIssueItem.isEmpty()) return ResponseEntity.notFound().build();
        else {
            var legalIssueResource = LegalIssueResourceFromEntityAssembler.toResourceFromEntity(legalIssueItem.get());
            return ResponseEntity.ok(legalIssueResource);
        }
    }


    @GetMapping
    public ResponseEntity<List<LegalIssueResource>> getAllLegalIssueByLegalConsultationId(@PathVariable Long legalConsultationId) {
        var getAllLegalIssuesByLegalConsultationIdQuery = new GetLegalIssuesByConsultationIdQuery(legalConsultationId);
        var legalIssues = legalIssueQueryService.handle(getAllLegalIssuesByLegalConsultationIdQuery);
        var legalIssueResources = legalIssues.stream().map(LegalIssueResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(legalIssueResources);
    }
}
