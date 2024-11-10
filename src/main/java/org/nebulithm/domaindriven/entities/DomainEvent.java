package org.nebulithm.domaindriven.entities;

import an.awesome.pipelinr.Notification;

import java.time.OffsetDateTime;
import java.util.UUID;

public abstract class DomainEvent implements Notification {
    UUID id;
    OffsetDateTime createdAt;
}
