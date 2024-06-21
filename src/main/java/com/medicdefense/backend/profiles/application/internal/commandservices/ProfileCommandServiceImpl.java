package com.medicdefense.backend.profiles.application.internal.commandservices;

import com.medicdefense.backend.profiles.domain.model.aggregate.Profile;
import com.medicdefense.backend.profiles.domain.model.commands.AddSpecialityCommand;
import com.medicdefense.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.medicdefense.backend.profiles.domain.model.valueobjects.EmailAddress;
import com.medicdefense.backend.profiles.domain.services.ProfileCommandService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.ProfileRepository;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.SpecialityItemsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final SpecialityItemsRepository specialityRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository, SpecialityItemsRepository specialityRepository) {
        this.profileRepository = profileRepository;
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        if (profileRepository.existsByEmail(emailAddress))
            throw new IllegalArgumentException(
                    "Profile with email " + command.email() + " already exists");
        var profile = new Profile(command);
        profileRepository.save(profile);
        return Optional.of(profile);
    }

    @Override
    public void handle(AddSpecialityCommand command) {
        if (!profileRepository.existsById(command.profileId())) {
            throw new IllegalArgumentException("Profile does not exist");
        }
        try {
            var name = command.specialityName();
            if(specialityRepository.existsByName(name)) {
                throw new IllegalArgumentException("Speciality already exists");
            }  else {
                profileRepository.findById(command.profileId()).map(profile -> {
                    profile.addSpeciality(command.specialityName());
                    profileRepository.save(profile);
                    System.out.println("Speciality added to Profile");
                    return profile;
                });
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while adding Speciality to Profile: " + e.getMessage());
        }
    }
}
