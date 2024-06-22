package com.medicdefense.backend.profiles.interfaces.rest.transform;

import com.medicdefense.backend.profiles.domain.model.commands.UpdateImgUrlCommand;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UpdateImgUrlResource;

public class UpdateImgUrlCommandFromResourceAssembler {
    public static UpdateImgUrlCommand ToCommandFromResource(Long profileId, UpdateImgUrlResource resource) {
        return new UpdateImgUrlCommand(resource.imgUrl(), profileId);
    }
}
