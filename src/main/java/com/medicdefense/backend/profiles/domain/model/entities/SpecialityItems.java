package com.medicdefense.backend.profiles.domain.model.entities;

import com.medicdefense.backend.profiles.domain.model.aggregate.Profile;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class SpecialityItems extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    @NotNull
    private Profile profile;

    @NotNull
    private String name;

    public SpecialityItems(Profile profile, String name) {
        this.profile = profile;
        this.name = name;
    }

    public SpecialityItems() {
        this.name = null;
    }
}
