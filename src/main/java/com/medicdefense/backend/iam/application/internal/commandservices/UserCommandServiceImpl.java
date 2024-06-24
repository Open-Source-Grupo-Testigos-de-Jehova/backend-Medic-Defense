package com.medicdefense.backend.iam.application.internal.commandservices;

import com.medicdefense.backend.iam.application.internal.outboundservices.acl.ExternalLawyerService;
import com.medicdefense.backend.iam.application.internal.outboundservices.acl.ExternalMedicService;
import com.medicdefense.backend.iam.application.internal.outboundservices.acl.ExternalMedicStudentService;
import com.medicdefense.backend.iam.application.internal.outboundservices.hashing.HashingService;
import com.medicdefense.backend.iam.application.internal.outboundservices.tokens.TokenService;
import com.medicdefense.backend.iam.domain.model.aggregates.User;
import com.medicdefense.backend.iam.domain.model.commands.AddANewLawyerCommand;
import com.medicdefense.backend.iam.domain.model.commands.SignInCommand;
import com.medicdefense.backend.iam.domain.model.commands.SignUpCommand;
import com.medicdefense.backend.iam.domain.model.entities.Role;
import com.medicdefense.backend.iam.domain.model.valueobjects.RecordId;
import com.medicdefense.backend.iam.domain.model.valueobjects.Roles;
import com.medicdefense.backend.iam.domain.services.UserCommandService;
import com.medicdefense.backend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.medicdefense.backend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;
    private final ExternalMedicService externalMedicService;
    private final ExternalMedicStudentService externalMedicStudentService;
    private final ExternalLawyerService externalLawyerService;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository, ExternalMedicService externalMedicService, ExternalMedicStudentService externalMedicStudentService, ExternalLawyerService externalLawyerService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
        this.externalMedicService = externalMedicService;
        this.externalMedicStudentService = externalMedicStudentService;
        this.externalLawyerService = externalLawyerService;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var stringRoles = List.of(command.role());
        var roles = new ArrayList<Role>();
        stringRoles.forEach(role -> {
            var storedRole = roleRepository.findByName(Roles.valueOf(role));
            storedRole.ifPresent(roles::add);
        });

        var RecordIdS = new RecordId();

        if(Objects.equals(command.role(), "ROLE_MEDIC"))
        {
            var recordId = externalMedicService.createMedic(
                    command.firstName(),
                    command.lastName(),
                    command.email(),
                    command.phoneNumber(),
                    command.DNI(),
                    command.image_url()
            );
            RecordIdS = recordId.get();
        }

        if(Objects.equals(command.role(), "ROLE_MEDIC_STUDENT"))
        {
            var recordId = externalMedicStudentService.createMedicStudent(
                    command.firstName(),
                    command.lastName(),
                    command.email(),
                    command.phoneNumber(),
                    command.DNI(),
                    command.image_url(),
                    command.university()
            );
            RecordIdS = recordId.get();
        }

        var user = new User(command.username(), hashingService.encode(command.password()), roles, RecordIdS);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<User> handle(AddANewLawyerCommand command) {
        if (userRepository.existsByUsername(command.email()))
            throw new RuntimeException("Username already exists");
        var stringRoles = List.of("ROLE_LAWYER");
        var roles = new ArrayList<Role>();
        stringRoles.forEach(role -> {
            var storedRole = roleRepository.findByName(Roles.valueOf(role));
            storedRole.ifPresent(roles::add);
        });

        var RecordId = externalLawyerService.createLawyer(
                command.firstName(),
                command.lastName(),
                command.email(),
                command.phoneNumber(),
                command.DNI(),
                command.image_url(),
                command.yearsExperience(),
                command.casesWon(),
                command.price()
        );
        var user = new User(command.email(), hashingService.encode(command.password()), roles, RecordId.get());
        userRepository.save(user);
        return userRepository.findByUsername(command.email());

    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}
