package emt.lab.budgetdeezer.shared_kernel.domain.base;

import org.springframework.lang.NonNull;

import java.time.Instant;

public interface iDomainEvent extends iDomainObject {

    @NonNull
    Instant occurredOn();
}
