package org.nebulithm.domaindriven.entities;

public interface AggregateManager<A extends Aggregate<A, T>, T> {
    Class<A> getManagedAggregateType();
    default boolean supportsAggregate(Class<?> aggregateClass) {
        return getManagedAggregateType() == aggregateClass;
    }
    void save(A aggregate);
}
