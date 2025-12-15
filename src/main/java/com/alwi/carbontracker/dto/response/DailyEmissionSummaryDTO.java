package com.alwi.carbontracker.dto.response;

import java.math.BigDecimal;

public interface DailyEmissionSummaryDTO {

    String getActivityType();

    BigDecimal getTotalEmissionKg();

    Long getTotalEvent();

    Long getSuccessCount();

    Long getFailedCount();
}
