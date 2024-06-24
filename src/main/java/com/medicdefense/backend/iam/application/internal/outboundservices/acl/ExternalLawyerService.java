package com.medicdefense.backend.iam.application.internal.outboundservices.acl;

import com.medicdefense.backend.iam.domain.model.valueobjects.RecordId;
import com.medicdefense.backend.profiles.interfaces.acl.LawyerContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalLawyerService {
    private final LawyerContextFacade lawyerContextFacade;

    public ExternalLawyerService(LawyerContextFacade lawyerContextFacade) {
        this.lawyerContextFacade = lawyerContextFacade;
    }

    public Optional<RecordId> createLawyer(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String DNI,
            String image_url,
            int yearsExperience,
            int wonCases,
            int price
    ) {
        var lawyerId = lawyerContextFacade.createLawyer(firstName, lastName, email, phoneNumber, DNI, image_url, yearsExperience, wonCases, price);
        if(lawyerId == null) return Optional.empty();
        return Optional.of(new RecordId(lawyerId));
    }
}
