package com.alwi.carbontracker.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyEmissionSummaryDTO implements Serializable {

    private String activityType;

    private BigDecimal totalEmissionKg;

    private Long totalEvent;

    private Long successCount;

    private Long failedCount;

    public DailyEmissionSummaryDTO(String activityType, BigDecimal totalEmissionKg, Long totalEvent, Long successCount, Long failedCount) {
        this.activityType = activityType;
        this.totalEmissionKg = totalEmissionKg;
        this.totalEvent = totalEvent;
        this.successCount = successCount;
        this.failedCount = failedCount;
    }

    public DailyEmissionSummaryDTO() {

    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public BigDecimal getTotalEmissionKg() {
        return totalEmissionKg;
    }

    public void setTotalEmissionKg(BigDecimal totalEmissionKg) {
        this.totalEmissionKg = totalEmissionKg;
    }

    public Long getTotalEvent() {
        return totalEvent;
    }

    public void setTotalEvent(Long totalEvent) {
        this.totalEvent = totalEvent;
    }

    public Long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Long successCount) {
        this.successCount = successCount;
    }

    public Long getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Long failedCount) {
        this.failedCount = failedCount;
    }
}
