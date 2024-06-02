package com.medicdefense.backend.legalcase.domain.services;

import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import com.medicdefense.backend.legalcase.domain.model.commands.CreateLegalCaseCommand;

import java.util.Optional;

public interface LegalCaseCommandService {
    Optional<LegalCase> handle(CreateLegalCaseCommand command);
}