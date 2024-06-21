package com.medicdefense.backend.profiles.interfaces.rest.resources;

public record ProfileResource (
        Long id,
        String fullName,
        String email,
        String phoneNumber,
        String DNI,
        String img_url
){
}
