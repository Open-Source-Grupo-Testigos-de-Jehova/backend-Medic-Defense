package com.medicdefense.backend.profiles.application.internal.queryservices;

import com.medicdefense.backend.profiles.domain.model.aggregate.Medic;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllMedicsQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetMedicByMedicDefenseRecordIdQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetMedicByProfileIdQuery;
import com.medicdefense.backend.profiles.domain.services.MedicQueryService;
import com.medicdefense.backend.profiles.infrasctructure.persistence.jpa.repositories.MedicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicQueryServiceImpl implements MedicQueryService {
    private final MedicRepository medicRepository;

    public MedicQueryServiceImpl(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }

    @Override
    public Optional<Medic> handle(GetMedicByMedicDefenseRecordIdQuery query) {
        return medicRepository.findByMedicDefenseMedicId(query.medicDefenseRecordId());
    }

    @Override
    public Optional<Medic> handle(GetMedicByProfileIdQuery query) {
        return medicRepository.findByProfileId(query.profileId());
    }

    @Override
    public List<Medic> handle(GetAllMedicsQuery query) {
        return medicRepository.findAll();
    }

}
