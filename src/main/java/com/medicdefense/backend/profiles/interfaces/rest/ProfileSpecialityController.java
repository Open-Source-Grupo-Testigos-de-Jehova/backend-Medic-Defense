package com.medicdefense.backend.profiles.interfaces.rest;

import com.medicdefense.backend.profiles.domain.model.commands.AddSpecialityCommand;
import com.medicdefense.backend.profiles.domain.model.queries.GetSpecialityByProfileIdAndNameQuery;
import com.medicdefense.backend.profiles.domain.services.ProfileCommandService;
import com.medicdefense.backend.profiles.domain.services.ProfileQueryService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.ProfileRepository;
import com.medicdefense.backend.profiles.interfaces.rest.resources.ProfileResource;
import com.medicdefense.backend.profiles.interfaces.rest.resources.SpecialityItemResource;
import com.medicdefense.backend.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import com.medicdefense.backend.profiles.interfaces.rest.transform.SpecialityItemResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/profiles/{profileId}/specialitys", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profile", description = "Profile Endpoints")
public class ProfileSpecialityController {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileSpecialityController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @PostMapping("/{specialityName}")
    public ResponseEntity<SpecialityItemResource> addSpecialityToProfile(@PathVariable Long profileId, @PathVariable String specialityName) {
        profileCommandService.handle(new AddSpecialityCommand(specialityName, profileId));
        var getSpecialityByProfileIdAndNameQuery = new GetSpecialityByProfileIdAndNameQuery(profileId, specialityName);
        var speciality = profileQueryService.handle(getSpecialityByProfileIdAndNameQuery);
        if (speciality.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            var specialityResource = SpecialityItemResourceFromEntityAssembler.toResourceFromEntity(speciality.get());
            return ResponseEntity.ok(specialityResource);
        }
    }
}
