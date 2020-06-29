package emt.lab.budgetdeezer.album_manager.application;

import emt.lab.budgetdeezer.shared_kernel.domain.base.iDomainEvent;
import emt.lab.budgetdeezer.shared_kernel.infrastructure.eventlog.DomainEventLogService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class LocalEventLogPublisher {

    private final DomainEventLogService domainEventLogService;

    public LocalEventLogPublisher(DomainEventLogService domainEventLogService) {
        this.domainEventLogService = domainEventLogService;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDomainEvent(@NonNull iDomainEvent domainEvent) {
        this.domainEventLogService.publishEventLog(domainEvent);
    }
}
