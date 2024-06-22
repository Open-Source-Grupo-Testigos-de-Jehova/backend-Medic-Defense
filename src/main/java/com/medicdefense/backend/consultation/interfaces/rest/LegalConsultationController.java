package com.medicdefense.backend.consultation.interfaces.rest;

import com.medicdefense.backend.consultation.domain.model.commands.DeleteLegalConsultationCommand;
import com.medicdefense.backend.consultation.domain.model.queries.GetAllLegalConsultationsQuery;
import com.medicdefense.backend.consultation.domain.model.queries.GetLegalConsultationsByIdQuery;
import com.medicdefense.backend.consultation.domain.services.LegalConsultationCommandService;
import com.medicdefense.backend.consultation.domain.services.LegalConsultationQueryService;
import com.medicdefense.backend.consultation.interfaces.rest.resources.CreateLegalConsultationResource;
import com.medicdefense.backend.consultation.interfaces.rest.resources.LegalConsultationResource;
import com.medicdefense.backend.consultation.interfaces.rest.transform.LegalConsultationResourceFromEntityAssembler;
import com.medicdefense.backend.consultation.interfaces.rest.transform.LegalCreateConsultationCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/legal-consultations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Legal Consultations", description = "Legal Consultation Management Endpoints")
public class LegalConsultationController {
    private final LegalConsultationCommandService legalConsultationCommandService;
    private final LegalConsultationQueryService legalConsultationQueryService;

    public LegalConsultationController(LegalConsultationCommandService legalConsultationCommandService, LegalConsultationQueryService legalConsultationQueryService) {
        this.legalConsultationCommandService = legalConsultationCommandService;
        this.legalConsultationQueryService = legalConsultationQueryService;
    }

    @PostMapping
    public ResponseEntity<LegalConsultationResource> createLegalConsultation(@RequestBody CreateLegalConsultationResource createLegalConsultationResource) {
        var createLegalConsultationCommand = LegalCreateConsultationCommandFromResourceAssembler.toCommandFromResource(createLegalConsultationResource);
        var legalConsultationId = legalConsultationCommandService.handle(createLegalConsultationCommand);
        if(legalConsultationId == null) {
            return ResponseEntity.badRequest().build();
        }
        var getLegalConsultationByIdQuery = new GetLegalConsultationsByIdQuery(legalConsultationId);
        var legalConsultation = legalConsultationQueryService.handle(getLegalConsultationByIdQuery);
        if(legalConsultation.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var legalConsultationResource = LegalConsultationResourceFromEntityAssembler.toResourceFromEntity(legalConsultation.get());
        return new ResponseEntity<>(legalConsultationResource, HttpStatus.CREATED);
    }

    @GetMapping("/{legalConsultationId}")
    public ResponseEntity<LegalConsultationResource> getLegalConsultationById(@PathVariable Long legalConsultationId) {
            var getLegalConsultationByIdQuery = new GetLegalConsultationsByIdQuery(legalConsultationId);
            var legalConsultation = legalConsultationQueryService.handle(getLegalConsultationByIdQuery);
            if(legalConsultation.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            var legalConsultationResource = LegalConsultationResourceFromEntityAssembler.toResourceFromEntity(legalConsultation.get());
            return ResponseEntity.ok(legalConsultationResource);
    }

    @GetMapping
    public ResponseEntity<List<LegalConsultationResource>> getAllLegalConsultations() {
        var legalConsultations = legalConsultationQueryService.handle(new GetAllLegalConsultationsQuery());
        var legalConsultationResources = legalConsultations.stream().map(LegalConsultationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(legalConsultationResources);
    }

    @DeleteMapping("/{legalConsultationId}")
    public ResponseEntity<?> deleteLegalConsultation(@PathVariable Long legalConsultationId) {
        var deleteLegalConsultationCommand = new DeleteLegalConsultationCommand(legalConsultationId);
        legalConsultationCommandService.handle(new DeleteLegalConsultationCommand(legalConsultationId));
        return ResponseEntity.ok("Legal Consultation deleted successfully");
    }
}
