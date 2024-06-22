package com.medicdefense.backend.profiles.interfaces.rest;


import com.medicdefense.backend.profiles.domain.model.commands.UpdateImgUrlCommand;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetProfileByIdQuery;
import com.medicdefense.backend.profiles.domain.services.ProfileCommandService;
import com.medicdefense.backend.profiles.domain.services.ProfileQueryService;
import com.medicdefense.backend.profiles.interfaces.rest.resources.*;
import com.medicdefense.backend.profiles.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profile", description = "Profile Endpoints")
public class ProfilesController {

    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesController(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @PostMapping
    public ResponseEntity<ProfileResource> createProfile(@RequestBody CreateProfileResource resource) {
        var createProfileCommand = CreateProfileCommandFromResourceAssembler.toCommandFromResource(resource);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<ProfileResource> getProfileById(@PathVariable Long profileId) {
        var getProfileByIdQuery = new GetProfileByIdQuery(profileId);
        var profile = profileQueryService.handle(getProfileByIdQuery);
        if (profile.isEmpty()) return ResponseEntity.notFound().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(profile.get());
        return ResponseEntity.ok(profileResource);
    }

    @GetMapping
    public ResponseEntity<List<ProfileResource>> getAllProfiles() {
        var getAllProfilesQuery = new GetAllProfilesQuery();
        var profiles = profileQueryService.handle(getAllProfilesQuery);
        var profileResources = profiles.stream()
                .map(ProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }

    @PutMapping("/{profileId}/DNI")
    public ResponseEntity<ProfileResource> updateDni(@PathVariable Long profileId, @RequestBody UpdateDNIResource dni) {
        var updateDniCommand = UpdateDNICommandFromResourceAssembler.ToCommandFromResource(profileId, dni);
        var updateDni = profileCommandService.handle(updateDniCommand);
        if (updateDni.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updateDni.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{profileId}/Email")
    public ResponseEntity<ProfileResource> updateEmail(@PathVariable Long profileId, @RequestBody UpdateEmailResource email) {
        var updateEmailCommand = UpdateEmailCommandFromResourceAssembler.ToCommandFromResource(profileId, email);
        var updateEmail = profileCommandService.handle(updateEmailCommand);
        if (updateEmail.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updateEmail.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{profileId}/ImgUrl")
    public ResponseEntity<ProfileResource> updateImgUrl(@PathVariable Long profileId, @RequestBody UpdateImgUrlResource imgUrl) {
        var updateImgUrlCommand = UpdateImgUrlCommandFromResourceAssembler.ToCommandFromResource(profileId, imgUrl);
        var updateImgUrl = profileCommandService.handle(updateImgUrlCommand);
        if (updateImgUrl.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updateImgUrl.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{profileId}/Name")
    public ResponseEntity<ProfileResource> updateName(@PathVariable Long profileId, @RequestBody UpdateNameResource name) {
        var updateNameCommand = UpdateNameCommandFromResourceAssembler.ToCommandFromResource(profileId, name);
        var updateName = profileCommandService.handle(updateNameCommand);
        if (updateName.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updateName.get());
        return ResponseEntity.ok(profileResource);
    }

    @PutMapping("/{profileId}/Phone")
    public ResponseEntity<ProfileResource> updatePhone(@PathVariable Long profileId, @RequestBody UpdatePhoneResource phone) {
        var updatePhoneCommand = UpdatePhoneCommandFromResourceAssembler.ToCommandFromResource(profileId, phone);
        var updatePhone = profileCommandService.handle(updatePhoneCommand);
        if (updatePhone.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = ProfileResourceFromEntityAssembler.toResourceFromEntity(updatePhone.get());
        return ResponseEntity.ok(profileResource);
    }
}
