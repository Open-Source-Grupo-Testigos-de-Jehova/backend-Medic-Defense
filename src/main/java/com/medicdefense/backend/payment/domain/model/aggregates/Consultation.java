package com.medicdefense.backend.payment.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@Entity(name = "ConsultationPayment")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String legalIssue;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAdd;

    @OneToMany(mappedBy = "consultation")
    private List<Payment> consultationPayments;

    public Consultation(Date date, String legalIssue) {
        this.date = date;
        this.legalIssue = legalIssue;
    }

    protected Consultation() {
    }

}