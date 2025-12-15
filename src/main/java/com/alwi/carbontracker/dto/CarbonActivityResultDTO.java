package com.alwi.carbontracker.dto;

import com.alwi.carbontracker.dto.request.EmissionResultDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarbonActivityResultDTO implements Serializable {

    private UUID carbonActivityId;

    private String activityType;

    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime activityTime;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    private EmissionResultDTO result;

    public CarbonActivityResultDTO(UUID carbonActivityId, String activityType, BigDecimal amount, LocalDateTime activityTime, String createdBy, LocalDateTime createdAt, EmissionResultDTO result) {
        this.carbonActivityId = carbonActivityId;
        this.activityType = activityType;
        this.amount = amount;
        this.activityTime = activityTime;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.result = result;
    }

    public CarbonActivityResultDTO() {

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

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalDateTime activityTime) {
        this.activityTime = activityTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public EmissionResultDTO getResult() {
        return result;
    }

    public void setResult(EmissionResultDTO result) {
        this.result = result;
    }
}

