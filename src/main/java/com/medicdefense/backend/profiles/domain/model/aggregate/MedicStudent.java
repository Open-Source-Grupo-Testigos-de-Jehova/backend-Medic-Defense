package com.medicdefense.backend.profiles.domain.model.aggregate;

import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.model.valueobjects.ProfileId;
import com.medicdefense.backend.profiles.domain.model.entities.University;
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

    public MedicStudent(ProfileId profileId, University university) {
        this();
        this.profileId = profileId;
        this.university = university;
        this.university.setMedicStudent(this);
    }

    public MedicStudent(long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    public void updateConsultationsMade(int consultationsMade) {
        ConsultationsMade = consultationsMade;
    }

    public void updatePaidServices(int paidServices) {
        PaidServices = paidServices;
    }

    public String getMedicStudentRecordId() {
        return this.medicDefenseMedicStudentId.RecordId();
    }

    public void AddConsultation() {
        ConsultationsMade++;
    }

    public void AddPaidService() {
        PaidServices++;
    }

    public Long getProfileId() {
        return this.profileId.profileId();
    }

    public void addUniversity(University university) {
        this.university = university;
        this.university.setMedicStudent(this);
    }
}
