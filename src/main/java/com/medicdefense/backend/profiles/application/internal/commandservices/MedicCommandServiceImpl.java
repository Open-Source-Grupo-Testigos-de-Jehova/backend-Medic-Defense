package com.medicdefense.backend.profiles.application.internal.commandservices;

import com.medicdefense.backend.profiles.application.internal.outboundservices.acl.ExternalProfileService;
import com.medicdefense.backend.profiles.domain.model.aggregate.Medic;
import com.medicdefense.backend.profiles.domain.model.commands.AddOneToConsultationMedicsMadeCommand;
import com.medicdefense.backend.profiles.domain.model.commands.AddOneToPaidServiceMedicCommand;
import com.medicdefense.backend.profiles.domain.model.commands.CreateMedicCommand;
import com.medicdefense.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.medicdefense.backend.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.medicdefense.backend.profiles.domain.model.valueobjects.EmailAddress;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.profiles.domain.services.MedicCommandService;
import com.medicdefense.backend.profiles.domain.services.ProfileCommandService;
import com.medicdefense.backend.profiles.domain.services.ProfileQueryService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.LawyerRepository;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.MedicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicCommandServiceImpl implements MedicCommandService {

    private final MedicRepository medicRepository;
    private final ExternalProfileService externalProfileService;

    public MedicCommandServiceImpl(MedicRepository medicRepository, ExternalProfileService externalProfileService) {
        this.medicRepository = medicRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public MedicDefenseRecordId handle(CreateMedicCommand command) {

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
                medicRepository.findByProfileId(profileId.get()).ifPresent(student -> {
                    throw new IllegalArgumentException("Student already exists");
                });
            }

            if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");

            var medic = new Medic(profileId.get());
            medicRepository.save(medic);
            return medic.getMedicDefenseMedicId();
    }

    @Override
    public Optional<Medic> handle(AddOneToConsultationMedicsMadeCommand command) {
        if (!medicRepository.existsByMedicDefenseMedicId(command.recordId())) {
            throw new IllegalArgumentException("Medic does not exist");
        }
        var result = medicRepository.findByMedicDefenseMedicId(command.recordId());
        if (result.isEmpty()) throw new IllegalArgumentException("Medic does not exist");
        var medicToUpdate = result.get();
        try {
            var updatedMedic = medicRepository.save(medicToUpdate.AddConsultation());
            return Optional.of(updatedMedic);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating medic: " + e.getMessage());
        }
    }

    @Override
    public Optional<Medic> handle(AddOneToPaidServiceMedicCommand command) {
        if (!medicRepository.existsByMedicDefenseMedicId(command.recordId())) {
            throw new IllegalArgumentException("Medic does not exist");
        }
        var result = medicRepository.findByMedicDefenseMedicId(command.recordId());
        if (result.isEmpty()) throw new IllegalArgumentException("Medic does not exist");
        var medicToUpdate = result.get();
        try {
            var updatedMedic = medicRepository.save(medicToUpdate.AddPaidService());
            return Optional.of(updatedMedic);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating medic: " + e.getMessage());
        }
    }
}
