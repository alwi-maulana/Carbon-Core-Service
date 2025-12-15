package com.alwi.carbontracker.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveCarbonActivityResponseDTO implements Serializable {
    private String activityType;
    private BigDecimal amount;
    private LocalDateTime activityTime;

    public SaveCarbonActivityResponseDTO(String activityType, BigDecimal amount, LocalDateTime activityTime) {
        this.activityType = activityType;
        this.amount = amount;
        this.activityTime = activityTime;
    }

    public SaveCarbonActivityResponseDTO() {
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
}
