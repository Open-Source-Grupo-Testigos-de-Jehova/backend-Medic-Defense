package com.medicdefense.backend.profiles.domain.services;

import com.medicdefense.backend.profiles.domain.model.aggregate.Medic;
import com.medicdefense.backend.profiles.domain.model.queries.GetAllMedicsQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetMedicByMedicDefenseRecordIdQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetMedicByProfileIdQuery;
import com.medicdefense.backend.profiles.domain.model.queries.GetMedicByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface MedicQueryService {
    Optional<Medic> handle(GetMedicByMedicDefenseRecordIdQuery query);
    Optional<Medic> handle(GetMedicByProfileIdQuery query);
    List<Medic> handle(GetAllMedicsQuery query);
    Optional<Medic> handle(GetMedicByUserIdQuery query);
}
