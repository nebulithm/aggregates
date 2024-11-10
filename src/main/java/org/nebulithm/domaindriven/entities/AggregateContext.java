package org.nebulithm.domaindriven.entities;

import java.util.List;

public interface AggregateContext {
    <T> T getService(Class<T> serviceClass);
    <T> List<T> getServices(Class<T> serviceClass);
    default <A extends Aggregate<A, T>, T> AggregateManager<A, T> getAggregateManager(Class<A> aggregateType) {
        return getServices(AggregateManager.class)
                .stream()
                .filter(manager -> manager.supportsAggregate(aggregateType))
                .map(manager -> (AggregateManager<A, T>) manager)
                .findFirst()
                .orElseThrow();
    }
}
