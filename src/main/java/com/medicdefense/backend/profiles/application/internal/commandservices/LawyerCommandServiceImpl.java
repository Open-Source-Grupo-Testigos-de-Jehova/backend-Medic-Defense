package com.medicdefense.backend.profiles.application.internal.commandservices;

import com.medicdefense.backend.profiles.application.internal.outboundservices.acl.ExternalProfileService;
import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.domain.model.commands.CreateLawyerCommand;
import com.medicdefense.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.medicdefense.backend.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.medicdefense.backend.profiles.domain.model.valueobjects.EmailAddress;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.profiles.domain.services.LawyerCommandService;
import com.medicdefense.backend.profiles.domain.services.ProfileCommandService;
import com.medicdefense.backend.profiles.domain.services.ProfileQueryService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.LawyerRepository;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.ProfileRepository;
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
}
