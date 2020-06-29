package emt.lab.budgetdeezer.shared_kernel.infrastructure.eventlog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import emt.lab.budgetdeezer.shared_kernel.domain.base.iDomainEvent;
import emt.lab.budgetdeezer.shared_kernel.infrastructure.jackson.RawJsonDeserializer;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

public class StoredDomainEvent {

    private static final int DOMAIN_EVENT_JSON_MAX_LENGTH = 1024;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("occurredOn")
    private Instant occurredOn;

    @JsonProperty("domainEventClass")
    private String domainEventClassName;

    @JsonProperty("domainEventBody")
    @JsonRawValue
    @JsonDeserialize(using = RawJsonDeserializer.class)
    private String domainEventBody;

    @Transient
    private Class<? extends iDomainEvent> domainEventClass;

    protected StoredDomainEvent() {
    }

    public StoredDomainEvent(@NonNull iDomainEvent domainEvent, @NonNull ObjectMapper objectMapper) {
        Objects.requireNonNull(domainEvent, "domainEvent cannot be null");
        Objects.requireNonNull(objectMapper, "objectMapper cannot be null");

        this.occurredOn = domainEvent.occurredOn();
        this.domainEventClass = domainEvent.getClass();
        this.domainEventClassName = this.domainEventClass.getSimpleName();

        try {
            this.domainEventBody = objectMapper.writeValueAsString(domainEvent);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Could not serialize domain event to JSON", ex);
        }

        if (this.domainEventBody.length() > DOMAIN_EVENT_JSON_MAX_LENGTH) {
            throw new IllegalArgumentException("Domain event JSON string is too long");
        }
    }

    @NonNull
    public Long id() {
        if (id == null) {
            throw new IllegalStateException("The StoredDomainEvent has not been saved yet");
        }
        return id;
    }

    @NonNull
    public iDomainEvent toDomainEvent(@NonNull ObjectMapper objectMapper) {
        return this.toDomainEvent(objectMapper, this.domainEventClass());
    }


    @NonNull
    public <T extends iDomainEvent> T toDomainEvent(@NonNull ObjectMapper objectMapper,
                                                    @NonNull Class<T> domainEventClass) {

        Objects.requireNonNull(objectMapper, "objectMapper cannot be null");
        Objects.requireNonNull(domainEventClass, "domainEventClass cannot be null");

        try {
            return objectMapper.readValue(domainEventBody, domainEventClass);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not deserialize domain event from JSON", ex);
        }
    }

    @NonNull
    public String toJsonString() {
        return domainEventBody;
    }


    @NonNull
    public JsonNode toJsonNode(@NonNull ObjectMapper objectMapper) {

        Objects.requireNonNull(objectMapper, "objectMapper cannot be null");

        try {
            return objectMapper.readTree(domainEventBody);
        } catch (IOException ex) {
            throw new IllegalStateException("Could not deserialize domain event from JSON", ex);
        }
    }

    @NonNull
    public Class<? extends iDomainEvent> domainEventClass() {

        if (this.domainEventClass == null) {
            this.domainEventClass = this.getDomainEventClass();
        }

        return this.domainEventClass;
    }


    @NonNull
    public String domainEventClassName() {
        return this.domainEventClassName;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends iDomainEvent> getDomainEventClass() {
        try {
            return (Class<? extends iDomainEvent>) Class.forName(domainEventClassName);
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Could not load domain event class", ex);
        }
    }


    @NonNull
    public Instant occurredOn() {
        return this.occurredOn;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d, domainEventClass=%s]", this.getClass().getSimpleName(), this.id, this.domainEventClassName);
    }
}
