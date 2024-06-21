package com.medicdefense.backend.profiles.application.internal.queryservices;

import com.medicdefense.backend.profiles.domain.model.aggregate.Profile;
import com.medicdefense.backend.profiles.domain.model.entities.SpecialityItems;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetProfileByIdQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetSpecialityByProfileIdAndNameQuery;
import com.medicdefense.backend.profiles.domain.services.ProfileQueryService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.Id());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmail(query.emailAddress());
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }

    @Override
    public Optional<SpecialityItems> handle(GetSpecialityByProfileIdAndNameQuery query) {
        return profileRepository
                .findById(query.ProfileId())
                .map(
                        profile -> profile
                                .getSpecialities()
                                .getSpecialityByName(query.specialityName()
                                )
                );
    }
}
