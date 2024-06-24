package com.medicdefense.backend.profiles.application.internal.outboundservices.acl;

import com.medicdefense.backend.iam.domain.model.commands.SignUpCommand;
import com.medicdefense.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.medicdefense.backend.iam.domain.model.queries.GetUserByUsernameQuery;
import com.medicdefense.backend.iam.interfaces.acl.IamContextFacade;
import com.medicdefense.backend.profiles.domain.model.valueobjects.UserId;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExternalUserService {
    public final IamContextFacade iamContextFacade;

    public ExternalUserService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<UserId> createUser(String username, String password) {
        var userId = iamContextFacade.createUser(username, password);
        if (userId == 0L) return Optional.empty();
        return Optional.of(new UserId(userId));
    }

    public Optional<UserId> createUser(String username, String password, List<String> roleNames) {
        var userId = iamContextFacade.createUser(username, password, roleNames);
        if (userId == 0L) return Optional.empty();
        return Optional.of(new UserId(userId));
    }

    public Optional<UserId> fetchUserIdByUsername(String username) {
        var userId = iamContextFacade.fetchUserIdByUsername(username);
        if (userId == 0L) return Optional.empty();
        return Optional.of(new UserId(userId));
    }

    public Optional<UserId> fetchUsernameByUserId(Long userId) {
        var username = iamContextFacade.fetchUsernameByUserId(userId);
        if (Strings.isBlank(username)) return Optional.empty();
        return Optional.of(new UserId(userId));
    }
}
