package com.medicdefense.backend.profiles.interfaces.acl;

import com.medicdefense.backend.profiles.domain.model.commands.CreateLawyerCommand;
import com.medicdefense.backend.profiles.domain.services.LawyerCommandService;
import org.springframework.stereotype.Service;

@Service
public class LawyerContextFacade {
    private final LawyerCommandService lawyerCommandService;

    public LawyerContextFacade(LawyerCommandService lawyerCommandService) {
        this.lawyerCommandService = lawyerCommandService;
    }

    public String createLawyer(
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
        var createLawyerCommand = new CreateLawyerCommand(firstName, lastName, email, phoneNumber, DNI, image_url, yearsExperience, wonCases, price);
        var lawyer = lawyerCommandService.handle(createLawyerCommand);
        if (lawyer.isEmpty()) return null;
        return lawyer.get().RecordId();
    }
}
