package emt.lab.budgetdeezer.artist_manager.domain.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import emt.lab.budgetdeezer.shared_kernel.domain.base.iDomainEvent;
import emt.lab.budgetdeezer.shared_kernel.infrastructure.eventlog.RemoteEventTranslator;
import emt.lab.budgetdeezer.shared_kernel.infrastructure.eventlog.StoredDomainEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumCreatedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public AlbumCreatedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Optional<iDomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, AlbumCreatedEvent.class));
    }
}
