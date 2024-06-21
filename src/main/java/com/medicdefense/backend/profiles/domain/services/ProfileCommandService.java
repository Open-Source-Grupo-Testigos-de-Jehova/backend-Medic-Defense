package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.Profile;
import com.medicdefense.backend.profiles.domain.model.commands.AddSpecialityCommand;
import com.medicdefense.backend.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    void handle(AddSpecialityCommand command);
}
