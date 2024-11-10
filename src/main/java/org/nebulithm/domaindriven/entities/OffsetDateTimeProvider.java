package org.nebulithm.domaindriven.entities;

import java.time.OffsetDateTime;

public class OffsetDateTimeProvider {
    public OffsetDateTime getNow() {
        return OffsetDateTime.now();
    }
}
