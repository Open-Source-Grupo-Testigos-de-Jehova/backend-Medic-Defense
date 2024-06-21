package com.medicdefense.backend.legalcase.application.internal.commandservices;

import com.medicdefense.backend.legalcase.domain.model.aggregates.LegalCase;
import com.medicdefense.backend.legalcase.domain.model.commands.CloseLegalCaseCommand;
import com.medicdefense.backend.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.medicdefense.backend.legalcase.domain.services.LegalCaseCommandService;
import com.medicdefense.backend.legalcase.infrastructure.persistence.jpa.LegalCaseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LegalCaseCommandServiceImpl implements LegalCaseCommandService {
    private final LegalCaseRepository legalCaseRepository;

    public LegalCaseCommandServiceImpl(LegalCaseRepository legalCaseRepository) {
        this.legalCaseRepository = legalCaseRepository;
    }
    @Override
    public Optional<LegalCase> handle(CreateLegalCaseCommand command) {
        var legalCase = new LegalCase(command);
        var createdLegalCase = legalCaseRepository.save(legalCase);
        return Optional.of(createdLegalCase);
    }

    @Override
    public Long handle(CloseLegalCaseCommand command) {
        legalCaseRepository.findById(command.id()).map(
                legalCase -> {
                    legalCase.close();
                    legalCaseRepository.save(legalCase);
                    return command.id();
                }
        ).orElseThrow(() -> new RuntimeException("Legal case not found"));
        return null;
    }
}