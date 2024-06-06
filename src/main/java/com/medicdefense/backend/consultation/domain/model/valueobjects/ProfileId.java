package com.medicdefense.backend.consultation.domain.model.valueobjects;

public record ProfileId(Long profileId) {
    public ProfileId {
        if (profileId < 0) {
            throw new IllegalArgumentException("Profile profileId cannot be negative");
        }
    }

    public ProfileId() {
        this(0L);
    }
}
