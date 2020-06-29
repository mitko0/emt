package emt.lab.budgetdeezer.shared_kernel.infrastructure.eventlog;

import com.fasterxml.jackson.databind.ObjectMapper;
import emt.lab.budgetdeezer.shared_kernel.domain.base.iDomainEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DomainEventLogService {

    private final KafkaTemplate<String, StoredDomainEvent> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public DomainEventLogService(KafkaTemplate<String, StoredDomainEvent> kafkaTemplate,
                                 ObjectMapper objectMapper) {

        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void publishEventLog(@NonNull iDomainEvent domainEvent) {
        StoredDomainEvent storedDomainEvent = new StoredDomainEvent(domainEvent, objectMapper);

//        String topic = storedDomainEvent.domainEventClass().getSimpleName();

        kafkaTemplate.send("DddTest", storedDomainEvent);
    }
}
