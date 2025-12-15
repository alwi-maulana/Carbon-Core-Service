package com.alwi.carbontracker.dto.projection;

import java.math.BigDecimal;

public interface DailyEmissionSummaryProjection {

    String getActivityType();

    BigDecimal getTotalEmissionKg();

    Long getTotalEvent();

    Long getSuccessCount();

    Long getFailedCount();
}
