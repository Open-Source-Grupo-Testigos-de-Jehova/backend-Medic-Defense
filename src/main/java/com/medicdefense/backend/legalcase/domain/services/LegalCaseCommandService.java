package com.medicdefense.backend.legalcase.domain.services;

import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import com.medicdefense.backend.legalcase.domain.model.commands.CloseLegalCaseCommand;
import com.medicdefense.backend.legalcase.domain.model.commands.CreateLegalCaseCommand;

import java.util.Optional;

/**
 * This interface is responsible for managing the LegalCase entities in the database.
 */

public interface LegalCaseCommandService {
    Optional<LegalCase> handle(CreateLegalCaseCommand command);
    Long handle(CloseLegalCaseCommand command);
}