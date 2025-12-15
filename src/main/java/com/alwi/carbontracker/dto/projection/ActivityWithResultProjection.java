package com.alwi.carbontracker.dto.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public interface ActivityWithResultProjection {
    UUID getCarbonActivityId();

    String getActivityType();

    BigDecimal getAmount();

    LocalDateTime getActivityTime();

    String getCreatedBy();

    LocalDateTime getCreatedAt();

    BigDecimal getEmissionKg();

    LocalDateTime getCalculatedAt();

    String getCalculationStatus();


}
