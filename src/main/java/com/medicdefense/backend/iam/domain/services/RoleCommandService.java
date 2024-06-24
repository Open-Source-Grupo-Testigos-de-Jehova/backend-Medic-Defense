package com.medicdefense.backend.iam.domain.services;

import com.medicdefense.backend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
