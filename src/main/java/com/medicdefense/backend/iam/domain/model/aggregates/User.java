package com.medicdefense.backend.iam.domain.model.aggregates;

import com.medicdefense.backend.iam.domain.model.entities.Role;
import com.medicdefense.backend.iam.domain.model.valueobjects.RecordId;
import com.medicdefense.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @NotBlank
    @Getter
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @Getter
    @NotBlank
    @Size(max = 120)
    private String password;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Embedded
    @Column(unique = true)
    private RecordId recordId;

    public User() {
        this.roles = new HashSet<>();
        this.recordId = new RecordId();
    }
    public User(String username, String password, RecordId recordId) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
        this.recordId = recordId;
    }

    public User(String username, String password, List<Role> roles, RecordId recordId) {
        this(username, password, recordId);
        addRoles(roles);
    }

    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public User addRoles(List<Role> roles) {
        //var validatedRoles = Role.validateRoleSet(roles);
        this.roles.addAll(roles);
        return this;
    }

    public String getRecordId() {
        return recordId.recordId();
    }
}