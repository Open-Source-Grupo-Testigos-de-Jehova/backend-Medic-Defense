package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.Profile;
import com.medicdefense.backend.profiles.domain.model.entities.SpecialityItems;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetProfileByIdQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetSpecialityByProfileIdAndNameQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<SpecialityItems> handle(GetSpecialityByProfileIdAndNameQuery query);
}
