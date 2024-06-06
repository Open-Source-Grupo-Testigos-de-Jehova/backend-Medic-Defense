package com.medicdefense.backend.payment.domain.model.aggregates;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private String legalIssue;
    private float amount;
    private String method;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;

    public Payment() {}

    public Payment(float amount, String method, Consultation consultation) {
        this.date = new Date();
        this.legalIssue = consultation.getLegalIssue();
        this.amount = amount;
        this.method = method;
        this.consultation = consultation;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getLegalIssue() {
        return legalIssue;
    }

    public float getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public Consultation getConsultation() {
        return consultation;
    }
}
