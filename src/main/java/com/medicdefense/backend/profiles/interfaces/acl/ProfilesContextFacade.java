package com.medicdefense.backend.profiles.interfaces.acl;

import com.medicdefense.backend.profiles.domain.model.commands.CreateProfileCommand;
import com.medicdefense.backend.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.medicdefense.backend.profiles.domain.model.valueobjects.EmailAddress;
import com.medicdefense.backend.profiles.domain.services.ProfileCommandService;
import com.medicdefense.backend.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(
            ProfileCommandService profileCommandService,
            ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    /**
     * Creates a new Profile
     *
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     */
    public Long createProfile(String firstName, String lastName, String email, String phoneNumber, String DNI, String image_url) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, phoneNumber, DNI, image_url);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }

    /**
     * Fetches the profile id by email
     *
     * @param email the email
     * @return the profile id
     */
    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();

    }
}