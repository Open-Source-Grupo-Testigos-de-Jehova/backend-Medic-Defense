package com.medicdefense.backend.profiles.interfaces.rest;

import com.medicdefense.backend.profiles.domain.model.commands.AddUniversityCommand;
import com.medicdefense.backend.profiles.domain.model.queries.GetUniversityByMedicStudentIdAndNameQuery;
import com.medicdefense.backend.profiles.domain.model.valueobjects.MedicDefenseRecordId;
import com.medicdefense.backend.profiles.domain.services.MedicStudentCommandService;
import com.medicdefense.backend.profiles.domain.services.MedicStudentQueryService;
import com.medicdefense.backend.profiles.interfaces.rest.resources.UniversityResource;
import com.medicdefense.backend.profiles.interfaces.rest.transform.SpecialityItemResourceFromEntityAssembler;
import com.medicdefense.backend.profiles.interfaces.rest.transform.UniversityResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/medic_student/{recordId}/universities", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Medic Student", description = "Profile Endpoints")
public class MedicStudentUniversityController {
    private final MedicStudentCommandService medicStudentCommandService;
    private final MedicStudentQueryService medicStudentQueryService;

    public MedicStudentUniversityController(MedicStudentCommandService medicStudentCommandService, MedicStudentQueryService medicStudentQueryService) {
        this.medicStudentCommandService = medicStudentCommandService;
        this.medicStudentQueryService = medicStudentQueryService;
    }


    @PostMapping("/{universityName}")
    public ResponseEntity<UniversityResource> addUniversityToMedicStudent(@PathVariable String recordId, @PathVariable String universityName) {
        var medicDefenseRecordId = new MedicDefenseRecordId(recordId);
        medicStudentCommandService.handle(new AddUniversityCommand(medicDefenseRecordId, universityName));
        var getUniversityByMedicStudentIdAndNameQuery = new GetUniversityByMedicStudentIdAndNameQuery(recordId, universityName);
        var university = medicStudentQueryService.handle(getUniversityByMedicStudentIdAndNameQuery);
        if (university.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            var universityResource = UniversityResourceFromEntityAssembler.toResourceFromEntity(university.get());
            return ResponseEntity.ok(universityResource);
        }
    }
}
