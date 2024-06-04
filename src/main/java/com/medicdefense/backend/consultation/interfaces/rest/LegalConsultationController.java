package com.medicdefense.backend.consultation.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/legal-consultations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Legal Consultations", description = "Legal Consultation Management Endpoints")
public class LegalConsultationController {
}
