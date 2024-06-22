package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.Profile;
import com.medicdefense.backend.profiles.domain.model.commands.*;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    void handle(AddSpecialityCommand command);
    Optional<Profile> handle(UpdateNameCommand command);
    Optional<Profile> handle(UpdateDniCommand command);
    Optional<Profile> handle(UpdateEmailCommand command);
    Optional<Profile> handle(UpdateImgUrlCommand command);
    Optional<Profile> handle(UpdatePhoneNumberCommand command);
}
