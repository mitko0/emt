package emt.lab.budgetdeezer.artist_manager.infrastructure.eventlog;

import com.fasterxml.jackson.databind.ObjectMapper;
import emt.lab.budgetdeezer.artist_manager.domain.events.AlbumCreatedEvent;
import emt.lab.budgetdeezer.shared_kernel.domain.base.iDomainEvent;
import emt.lab.budgetdeezer.shared_kernel.infrastructure.eventlog.StoredDomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

@Service
public class LocalEventProcessor {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final TransactionTemplate transactionTemplate;
    private final ObjectMapper objectMapper;

    public LocalEventProcessor(ApplicationEventPublisher applicationEventPublisher, TransactionTemplate transactionTemplate, ObjectMapper objectMapper) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.transactionTemplate = transactionTemplate;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "DddTest", groupId = "group_id")
    public void onDomainEvent(StoredDomainEvent event) {

        this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                publishEvent(event);
            }
        });
    }

    private void publishEvent(@NonNull StoredDomainEvent event) {
        Optional<AlbumCreatedEvent> translatedEvent = Optional.of(event.toDomainEvent(objectMapper, AlbumCreatedEvent.class));

        translatedEvent.ifPresent(domainEvent -> applicationEventPublisher.publishEvent(domainEvent));
    }
}
