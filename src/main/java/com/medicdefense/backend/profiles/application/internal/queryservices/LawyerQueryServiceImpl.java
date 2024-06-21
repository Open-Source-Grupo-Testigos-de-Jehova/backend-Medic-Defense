package com.medicdefense.backend.profiles.application.internal.queryservices;


import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllLawyersQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetLawyerByMedicDefenseRecordIdQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetLawyerByProfileIdQuery;
import com.medicdefense.backend.profiles.domain.services.LawyerQueryService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.LawyerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LawyerQueryServiceImpl implements LawyerQueryService {
    private final LawyerRepository lawyerRepository;

    public LawyerQueryServiceImpl(LawyerRepository lawyerRepository) {
        this.lawyerRepository = lawyerRepository;
    }

    @Override
    public Optional<Lawyer> handle(GetLawyerByMedicDefenseRecordIdQuery query) {
        return lawyerRepository.findByMedicDefenseRecordId(query.medicDefenseRecordId());
    }

    @Override
    public Optional<Lawyer> handle(GetLawyerByProfileIdQuery query) {
        return lawyerRepository.findByProfileId(query.profileId());
    }

    @Override
    public List<Lawyer> handle(GetAllLawyersQuery query) {
        return lawyerRepository.findAll();
    }
}
