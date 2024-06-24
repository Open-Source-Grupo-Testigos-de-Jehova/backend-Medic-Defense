package com.medicdefense.backend.profiles.domain.model.aggregate;

import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.profiles.domain.model.entities.University;
import com.medicdefense.backend.profiles.domain.model.valueobjects.UserId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MedicStudent extends AuditableAbstractAggregateRoot<MedicStudent> {

    @Embedded
    @Column(name = "medic_defense_medic_student_id")
    private final MedicDefenseRecordId medicDefenseMedicStudentId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private UserId userId;

    @NotNull
    private int ConsultationsMade;

    @NotNull
    private int PaidServices;

    @OneToOne(mappedBy = "medicStudent", cascade = CascadeType.ALL, orphanRemoval = true)
    private University university;

    public MedicStudent() {
        this.medicDefenseMedicStudentId = new MedicDefenseRecordId();
        this.profileId = new ProfileId();
        this.university = new University();
        this.university.setMedicStudent(this);
        ConsultationsMade = 0;
        PaidServices = 0;
    }

    public MedicStudent(ProfileId profileId, University university, UserId userId) {
        this();
        this.profileId = profileId;
        this.university = university;
        this.university.setMedicStudent(this);
        this.userId = userId;
    }

    public MedicStudent(long profileId, Long userId) {
        this();
        this.profileId = new ProfileId(profileId);
        this.userId = new UserId(userId);
    }

    public String getMedicStudentRecordId() {
        return this.medicDefenseMedicStudentId.RecordId();
    }

    public MedicStudent AddConsultation() {
        ConsultationsMade++;
        return this;
    }

    public MedicStudent AddPaidService() {
        PaidServices++;
        return this;
    }

    public Long getProfileId() {
        return this.profileId.profileId();
    }

    public Long getUserId() {
        return this.userId.userId();
    }

    /**
     * Add a university to the medic student
     * @param university The university to add
     */
    public void addUniversity(University university) {
        this.university = university;
        this.university.setMedicStudent(this);
    }
}
