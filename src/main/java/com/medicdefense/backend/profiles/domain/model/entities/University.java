package com.medicdefense.backend.profiles.domain.model.entities;

import com.medicdefense.backend.profiles.domain.model.aggregate.MedicStudent;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToOne
    @JoinColumn(name = "medic_student_id")
    private MedicStudent medicStudent;

    public University() {
        this.name = "";
    }

    public University(String name)
    {
        this();
        this.name = name;

    }

    public void addUniversity(String name) {
        this.name = name;
    }

    public String getUniversityName() {
        return this.name;
    }
}
