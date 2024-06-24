package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.Lawyer;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllLawyersQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetLawyerByMedicDefenseRecordIdQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetLawyerByProfileIdQuery;

import java.util.List;
import java.util.Optional;

public interface LawyerQueryService {
    Optional<Lawyer> handle(GetLawyerByMedicDefenseRecordIdQuery query);
    Optional<Lawyer> handle(GetLawyerByProfileIdQuery query);
    List<Lawyer> handle(GetAllLawyersQuery query);
}
