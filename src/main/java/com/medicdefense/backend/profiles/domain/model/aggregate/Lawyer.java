package com.medicdefense.backend.profiles.domain.model.aggregate;

import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.UserId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Lawyer extends AuditableAbstractAggregateRoot<Lawyer> {

    @Embedded
    @Column(name = "medic_defense_lawyer_id")
    private final MedicDefenseRecordId medicDefenseRecordId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private UserId userId;

    @NotNull
    private int YearsExperience;

    @NotNull
    private int CasesWon;

    @NotNull
    private int Price;

    public Lawyer() {
        this.medicDefenseRecordId = new MedicDefenseRecordId();
        this.profileId = new ProfileId();
        this.userId = new UserId();
        YearsExperience = 0;
        CasesWon = 0;
        Price = 0;
    }

    public Lawyer(ProfileId profileId, UserId userId, int yearsExperience, int casesWon, int price) {
        this();
        this.profileId = profileId;
        this.userId = userId;
        YearsExperience = yearsExperience;
        CasesWon = casesWon;
        Price = price;
    }

    public Lawyer(long profileId, long userId, int yearsExperience, int casesWon, int price) {
        this();
        this.profileId = new ProfileId(profileId);
        this.userId = new UserId(userId);
        YearsExperience = yearsExperience;
        CasesWon = casesWon;
        Price = price;
    }

    public Lawyer updateYearsExperience(int yearsExperience)
    {
        YearsExperience = yearsExperience;
        return this;
    }

    public Lawyer updateCasesWon(int casesWon) {
        CasesWon = casesWon;
        return this;
    }

    public Lawyer updatePrice(int price)
    {
        Price = price;
        return this;
    }

    public Long getProfileId() {
        return this.profileId.profileId();
    }

    public Long getUserId()
    {
        return this.userId.userId();
    }

    public String getLawyerRecordId() {
        return this.medicDefenseRecordId.RecordId();
    }
}