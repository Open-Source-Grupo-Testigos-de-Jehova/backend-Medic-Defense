package com.medicdefense.backend.profiles.application.internal.commandservices;

import com.medicdefense.backend.profiles.application.internal.outboundservices.acl.ExternalProfileService;
import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.domain.model.commands.*;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.services.LawyerCommandService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.LawyerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LawyerCommandServiceImpl implements LawyerCommandService {

    private final LawyerRepository lawyerRepository;
    private final ExternalProfileService externalProfileService;

    public LawyerCommandServiceImpl(LawyerRepository lawyerRepository, ExternalProfileService externalProfileService) {
        this.lawyerRepository = lawyerRepository;
        this.externalProfileService = externalProfileService;
    }


    @Override
    public MedicDefenseRecordId handle(CreateLawyerCommand command) {

        // Fetch medicDefenseId by email
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());

        // If medicDefenseId is empty, create profile
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(
                    command.firstName(),
                    command.lastName(),
                    command.email(),
                    command.phoneNumber(),
                    command.DNI(),
                    command.image_url()
            );
        } else {
            lawyerRepository.findByProfileId(profileId.get()).ifPresent(student -> {
                throw new IllegalArgumentException("Student already exists");
            });
        }

        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");

        var lawyer = new Lawyer(
                profileId.get(), command.yearsExperience(), command.casesWon(), command.price());
        lawyerRepository.save(lawyer);
        return lawyer.getMedicDefenseRecordId();
    }

    @Override
    public Optional<Lawyer> handle(UpdatePriceCommand command) {
        if (!lawyerRepository.existsByMedicDefenseRecordId(command.recordId())) {
            throw new IllegalArgumentException("Lawyer does not exist");
        }
        var result = lawyerRepository.findByMedicDefenseRecordId(command.recordId());
        if (result.isEmpty()) throw new IllegalArgumentException("Lawyer does not exist");
        var lawyerToUpdate = result.get();
        try {
            var updateLawyer = lawyerRepository.save(lawyerToUpdate.updatePrice(command.Price()));
            return Optional.of(updateLawyer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating price: " + e.getMessage());
        }
    }

    @Override
    public Optional<Lawyer> handle(UpdateWonCasesCommand command) {
        if (!lawyerRepository.existsByMedicDefenseRecordId(command.recordId())) {
            throw new IllegalArgumentException("Lawyer does not exist");
        }
        var result = lawyerRepository.findByMedicDefenseRecordId(command.recordId());
        if (result.isEmpty()) throw new IllegalArgumentException("Lawyer does not exist");
        var lawyerToUpdate = result.get();
        try {
            var updateLawyer = lawyerRepository.save(lawyerToUpdate.updateCasesWon(command.wonCases()));
            return Optional.of(updateLawyer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating cases won: " + e.getMessage());
        }
    }

    @Override
    public Optional<Lawyer> handle(UpdateYearExperienceCommand command) {
        if (!lawyerRepository.existsByMedicDefenseRecordId(command.recordId())) {
            throw new IllegalArgumentException("Lawyer does not exist");
        }
        var result = lawyerRepository.findByMedicDefenseRecordId(command.recordId());
        if (result.isEmpty()) throw new IllegalArgumentException("Lawyer does not exist");
        var lawyerToUpdate = result.get();
        try {
            var updateLawyer = lawyerRepository.save(lawyerToUpdate.updateYearsExperience(command.YearExperience()));
            return Optional.of(updateLawyer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating years of experience: " + e.getMessage());
        }
    }
}
