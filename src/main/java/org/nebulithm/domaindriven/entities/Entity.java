package org.nebulithm.domaindriven.entities;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class Entity<T> {
    T id;
}
