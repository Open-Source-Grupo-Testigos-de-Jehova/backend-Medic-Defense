package com.medicdefense.backend.payment.domain.model.aggregates;

import com.medicdefense.backend.payment.domain.model.commands.CreatePaymentCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Payment  extends AbstractAggregateRoot<Payment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private String method;

    @ManyToOne
    @JoinColumn(name = "consultation_id" ,nullable = false)
    private Consultation consultation;

    public Payment() {

    }
    public Payment(CreatePaymentCommand command, Consultation consultation) {
        this.consultation = consultation;
        this.amount = command.amount();
        this.method = command.method();
    }
}
