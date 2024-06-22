package com.medicdefense.backend.consultation.domain.model.valueobjects;

import com.medicdefense.backend.consultation.domain.model.aggregate.LegalConsultation;
import com.medicdefense.backend.consultation.domain.model.entities.LegalIssueItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class LegalIssue {
    @OneToMany(mappedBy = "legalConsultation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LegalIssueItem> legalIssueItems;

    public LegalIssue() {
        this.legalIssueItems = new ArrayList<>();
    }

    public void addLegalIssueItem(String consultation, LegalConsultation legalConsultation) {
        var legalIssueItem = new LegalIssueItem(legalConsultation, consultation);
        this.legalIssueItems.add(legalIssueItem);
    }

    public LegalIssueItem getLegalIssueItemById(long id) {
        return legalIssueItems.stream()
                .filter(legalIssueItem -> legalIssueItem.getId() == id)
                .findFirst().orElse(null);
    }

    public LegalIssueItem getLegalIssueItemByName(String name) {
        return legalIssueItems.stream()
                .filter(legalIssueItem -> legalIssueItem.getConsultation().equals(name))
                .findFirst().orElse(null);
    }

    public void removeLegalIssueItemById(long id) {
        legalIssueItems.removeIf(legalIssueItem -> legalIssueItem.getId() == id);
    }

    public void closeLegalIssueItemById(long id) {
        var legalIssueItem = getLegalIssueItemById(id);
        if (legalIssueItem != null) {
            legalIssueItem.close();
        }
    }

    public List<LegalIssueItem> getAllLegalIssueItemsByConsultationId(long consultationId) {
        return legalIssueItems.stream()
                .filter(legalIssueItem -> legalIssueItem.getLegalConsultation().getId() == consultationId)
                .toList();
    }

    public void responseLegalIssueItemById(long id, String response) {
        var legalIssueItem = getLegalIssueItemById(id);
        if (legalIssueItem != null) {
            legalIssueItem.respond(response);
        }
    }
}
