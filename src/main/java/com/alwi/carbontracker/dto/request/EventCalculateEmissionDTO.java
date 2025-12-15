package com.alwi.carbontracker.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventCalculateEmissionDTO implements Serializable {

    private UUID carbonActivityId;

    private String activityType;

    private BigDecimal amount;

    private String unit;

    private BigDecimal factorKg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public EventCalculateEmissionDTO(UUID carbonActivityId, String activityType, BigDecimal amount, String unit, BigDecimal factorKg, LocalDateTime timestamp) {
        this.carbonActivityId = carbonActivityId;
        this.activityType = activityType;
        this.amount = amount;
        this.unit = unit;
        this.factorKg = factorKg;
        this.timestamp = timestamp;
    }

    public EventCalculateEmissionDTO() {

    }

    public UUID getCarbonActivityId() {
        return carbonActivityId;
    }

    public void setCarbonActivityId(UUID carbonActivityId) {
        this.carbonActivityId = carbonActivityId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getFactorKg() {
        return factorKg;
    }

    public void setFactorKg(BigDecimal factorKg) {
        this.factorKg = factorKg;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
