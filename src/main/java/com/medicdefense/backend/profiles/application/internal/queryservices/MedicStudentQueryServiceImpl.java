package com.medicdefense.backend.profiles.application.internal.queryservices;

import com.medicdefense.backend.profiles.domain.model.aggregate.MedicStudent;
import com.medicdefense.backend.profiles.domain.model.entities.University;
import com.medicdefense.backend.profiles.domain.model.queries.*;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.services.MedicStudentQueryService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.MedicStudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicStudentQueryServiceImpl implements MedicStudentQueryService {
    private final MedicStudentRepository medicStudentRepository;

    public MedicStudentQueryServiceImpl(MedicStudentRepository medicStudentRepository) {
        this.medicStudentRepository = medicStudentRepository;
    }

    @Override
    public Optional<MedicStudent> handle(GetMedicStudentByMedicDefenseRecordIdQuery query) {
        return medicStudentRepository.findByMedicDefenseMedicStudentId(query.medicDefenseRecordId());
    }

    @Override
    public Optional<MedicStudent> handle(GetMedicStudentByProfileIdQuery query) {
        return medicStudentRepository.findByProfileId(query.profileId());
    }

    @Override
    public List<MedicStudent> handle(GetAllMedicStudentsQuery query) {
        return medicStudentRepository.findAll();
    }

    @Override
    public Optional<University> handle(GetUniversityByMedicStudentIdAndNameQuery query) {
        var medicStudentId = new MedicDefenseRecordId(query.medicStudentId());
        return medicStudentRepository
                .findByMedicDefenseMedicStudentId(medicStudentId)
                .map(MedicStudent::getUniversity);
    }

    @Override
    public Optional<MedicStudent> handle(GetMedicStudentByUserIdQuery query) {
        return medicStudentRepository.findByUserId(query.userId());
    }
}
