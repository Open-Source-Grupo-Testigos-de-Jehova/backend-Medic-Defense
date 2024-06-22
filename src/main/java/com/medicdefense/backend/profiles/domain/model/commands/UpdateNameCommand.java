package com.medicdefense.backend.profiles.domain.model.commands;

public record UpdateNameCommand (String firstName, String lastName, Long profileId){
}
