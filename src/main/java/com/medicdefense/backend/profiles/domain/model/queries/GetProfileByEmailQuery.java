package com.medicdefense.backend.profiles.domain.model.queries;

import com.medicdefense.backend.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery (EmailAddress emailAddress){
}
