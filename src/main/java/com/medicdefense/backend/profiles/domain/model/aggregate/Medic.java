package com.medicdefense.backend.profiles.domain.model.aggregate;

import com.medicdefense.backend.iam.domain.model.aggregates.User;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.UserId;
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

    @Embedded
    private UserId userId;

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

    public Medic(ProfileId profileId, UserId userId) {
        this();
        this.profileId = profileId;
        this.userId = userId;
    }

    public Medic(long profileId, Long userId) {
        this();
        this.profileId = new ProfileId(profileId);
        this.userId = new UserId(userId);
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

    /**
     * @return The user id of the medic.
     */
    public Long getUserId() {
        return this.userId.userId();
    }
}
