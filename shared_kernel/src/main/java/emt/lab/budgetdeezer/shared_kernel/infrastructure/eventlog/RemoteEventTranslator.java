package emt.lab.budgetdeezer.shared_kernel.infrastructure.eventlog;

import emt.lab.budgetdeezer.shared_kernel.domain.base.iDomainEvent;

import java.util.Optional;

public interface RemoteEventTranslator {

    Optional<iDomainEvent> translate(StoredDomainEvent remoteEvent);

}
