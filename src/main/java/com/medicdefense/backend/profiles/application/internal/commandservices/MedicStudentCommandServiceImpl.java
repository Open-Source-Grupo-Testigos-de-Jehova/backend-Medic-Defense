package com.medicdefense.backend.profiles.application.internal.commandservices;

import com.medicdefense.backend.profiles.application.internal.outboundservices.acl.ExternalProfileService;
import com.medicdefense.backend.profiles.domain.model.aggregate.MedicStudent;
import com.medicdefense.backend.profiles.domain.model.commands.AddOneToConsultationMedicStudentMadeCommand;
import com.medicdefense.backend.profiles.domain.model.commands.AddOneToPaidServiceMedicStudentCommand;
import com.medicdefense.backend.profiles.domain.model.commands.AddUniversityCommand;
import com.medicdefense.backend.profiles.domain.model.commands.CreateMedicStudentCommand;
import com.medicdefense.backend.profiles.domain.model.entities.University;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.services.MedicStudentCommandService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.MedicStudentRepository;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicStudentCommandServiceImpl implements MedicStudentCommandService {
    private final MedicStudentRepository medicStudentRepository;
    private final ExternalProfileService externalProfileService;
    private final UniversityRepository universityRepository;

    public MedicStudentCommandServiceImpl(MedicStudentRepository medicStudentRepository, ExternalProfileService externalProfileService, UniversityRepository universityRepository) {
        this.medicStudentRepository = medicStudentRepository;
        this.externalProfileService = externalProfileService;
        this.universityRepository = universityRepository;
    }

    @Override
    public Optional<MedicDefenseRecordId> handle(CreateMedicStudentCommand command) {
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());

        // If medicDefenseId is empty, create profile
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(
                    command.firstName(),
                    command.lastName(),
                    command.email(),
                    command.phoneNumber(),
                    command.DNI(),
                    command.image_url()
            );
        } else {
            medicStudentRepository.findByProfileId(profileId.get()).ifPresent(student -> {
                throw new IllegalArgumentException("Student already exists");
            });
        }

        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");

        var university = new University();

        if (universityRepository.existsByName(command.universityName())) {
            university = universityRepository.findByName(command.universityName()).get();
        } else {
            university = new University(command.universityName());
            universityRepository.save(university);
        }

        var medicStudent = new MedicStudent(profileId.get(), university);

        university.setMedicStudent(medicStudent);
        medicStudentRepository.save(medicStudent);

        return Optional.of(medicStudent.getMedicDefenseMedicStudentId());
    }

    @Override
    public void handle(AddUniversityCommand command) {
        if (!medicStudentRepository.existsByMedicDefenseMedicStudentId(command.medicStudentId())) {
            throw new IllegalArgumentException("Medic Student does not exist");
        }
        try {
            if(universityRepository.existsByName(command.universityName())) {
                universityRepository.findByName(command.universityName())
                        .map(university ->
                        {
                            medicStudentRepository.findByMedicDefenseMedicStudentId(command.medicStudentId())
                                    .map(medic ->
                                    {
                                        medic.addUniversity(university);
                                        medicStudentRepository.save(medic);
                                        System.out.println("University added to Student");
                                        return medic;
                                    });
                            return university;
                        });
            } else {
                var university = new University(command.universityName());
                medicStudentRepository.findByMedicDefenseMedicStudentId(command.medicStudentId()).map(medic -> {
                    medic.addUniversity(university);
                    medicStudentRepository.save(medic);
                    System.out.println("University added to Student");
                    return medic;
                });
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while adding University to Medic Student: " + e.getMessage());
        }
    }

    @Override
    public Optional<MedicStudent> handle(AddOneToConsultationMedicStudentMadeCommand command) {
        if (!medicStudentRepository.existsByMedicDefenseMedicStudentId(command.recordId())) {
            throw new IllegalArgumentException("Medic Student does not exist");
        }
        var result = medicStudentRepository.findByMedicDefenseMedicStudentId(command.recordId());
        if (result.isEmpty()) throw new IllegalArgumentException("Medic Student does not exist");
        var medicStudentToUpdate = result.get();
        try {
            var updatedMedicStudent = medicStudentRepository.save(medicStudentToUpdate.AddConsultation());
            return Optional.of(updatedMedicStudent);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating medic student: " + e.getMessage());
        }
    }

    @Override
    public Optional<MedicStudent> handle(AddOneToPaidServiceMedicStudentCommand command) {
        if (!medicStudentRepository.existsByMedicDefenseMedicStudentId(command.recordId())) {
            throw new IllegalArgumentException("Medic Student does not exist");
        }
        var result = medicStudentRepository.findByMedicDefenseMedicStudentId(command.recordId());
        if (result.isEmpty()) throw new IllegalArgumentException("Medic Student does not exist");
        var medicStudentToUpdate = result.get();
        try {
            var updatedMedicStudent = medicStudentRepository.save(medicStudentToUpdate.AddPaidService());
            return Optional.of(updatedMedicStudent);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating medic student: " + e.getMessage());
        }
    }
}
