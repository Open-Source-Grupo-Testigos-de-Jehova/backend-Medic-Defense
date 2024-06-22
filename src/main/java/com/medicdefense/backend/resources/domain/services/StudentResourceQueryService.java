package com.medicdefense.backend.resources.domain.services;

import com.medicdefense.backend.resources.domain.model.entities.StudentResource;
import com.medicdefense.backend.resources.domain.model.queries.GetAllStudentResourcesQuery;
import com.medicdefense.backend.resources.domain.model.queries.GetStudentResourcesByStudentId;

import java.util.List;

public interface StudentResourceQueryService {
    List<StudentResource> handle(GetAllStudentResourcesQuery query);
    List<StudentResource> handle(GetStudentResourcesByStudentId query);
}
