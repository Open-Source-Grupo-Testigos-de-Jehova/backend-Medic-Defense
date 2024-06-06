package com.medicdefense.backend.payment.domain.model.aggregates;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String legalIssue;

    public Consultation(Date date, String s) {


    }

    public Consultation() {

    }

    public Long getId() {
        return id;
    }

    public String getLegalIssue() {
        return legalIssue;
    }
}
