package com.medicdefense.backend.iam.domain.model.queries;

import com.medicdefense.backend.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles roleName) {
}
