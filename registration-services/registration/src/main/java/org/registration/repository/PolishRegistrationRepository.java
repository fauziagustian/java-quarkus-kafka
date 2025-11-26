package org.registration.repository;

import org.registration.entity.PolishRegistration;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PolishRegistrationRepository implements PanacheRepository<PolishRegistration> {
}
