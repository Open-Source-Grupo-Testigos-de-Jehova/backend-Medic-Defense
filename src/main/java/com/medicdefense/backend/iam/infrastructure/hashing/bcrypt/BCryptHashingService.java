package com.medicdefense.backend.iam.infrastructure.hashing.bcrypt;

import com.medicdefense.backend.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
