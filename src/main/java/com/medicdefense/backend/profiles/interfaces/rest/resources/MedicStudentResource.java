package com.medicdefense.backend.profiles.interfaces.rest.resources;

public record MedicStudentResource(
                String medicDefenseMedicRecordId,
                Long profileId,
                Long userId,
                Long universityId,
                int consultationsMade,
                int paidServices
        ){
}
