package com.medicdefense.backend.profiles.domain.model.aggregate;

import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Medic extends AuditableAbstractAggregateRoot<Medic> {

    @Embedded
    @Column(name = "medic_defense_medic_id")
    private final MedicDefenseRecordId medicDefenseMedicId;

    @Embedded
    private ProfileId profileId;

    @NotNull
    private int ConsultationsMade;

    @NotNull
    private int PaidServices;

    public Medic(){
        this.medicDefenseMedicId = new MedicDefenseRecordId();
        this.profileId = new ProfileId();
        ConsultationsMade = 0;
        PaidServices = 0;
    }

    public Medic(ProfileId profileId) {
        this();
        this.profileId = profileId;
    }

    public Medic(long profileId, Long userId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    public String getMedicRecordId() {
        return this.medicDefenseMedicId.RecordId();
    }

    /**
     * Adds a consultation to the medic.
     * @return The updated medic.
     */
    public Medic AddConsultation() {
        ConsultationsMade++;
        return this;
    }

    /**
     * Adds a paid service to the medic.
     * @return The updated medic.
     */
    public Medic AddPaidService() {
        PaidServices++;
        return this;
    }

    /**
     * @return The profile id of the medic.
     */
    public Long getProfileId() {
        return this.profileId.profileId();
    }

}
