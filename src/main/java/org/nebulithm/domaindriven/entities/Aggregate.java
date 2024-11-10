package org.nebulithm.domaindriven.entities;

import an.awesome.pipelinr.Pipeline;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

@SuperBuilder
public abstract class Aggregate<A extends Aggregate<A,T>, T> extends Entity<T> {
    @Getter
    OffsetDateTime createdAt;
    @Getter
    OffsetDateTime lastModifiedAt;
    boolean persisted = false;
    AggregateContext context;
    List<DomainEvent> domainEvents = new LinkedList<>();

    public void save() {
        ensureAggregateIsPersisted();
        updateAggregate();
        processEvents();
    }

    protected abstract Class<A> getAggregateClass();

    private A asAggregateSubClass() {
        return (A) this;
    }

    private void ensureAggregateIsPersisted() {
        if (!persisted) {
            persisted = true;
            createdAt = context.getService(OffsetDateTimeProvider.class).getNow();
        }
    }

    private void processEvents() {
        domainEvents.forEach(event -> context.getService(Pipeline.class).send(event));
        domainEvents.clear();
    }

    private void updateAggregate() {
        lastModifiedAt = context.getService(OffsetDateTimeProvider.class).getNow();
        context.getAggregateManager(getAggregateClass()).save(asAggregateSubClass());
    }
}
