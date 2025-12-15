package com.alwi.carbontracker.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmissionResultDTO implements Serializable {
    private String carbonActivityId;
    private BigDecimal emissionKg;
    private LocalDateTime calculatedAt;
    private String calculationStatus;
    private String calculationType;

    public EmissionResultDTO(String carbonActivityId, BigDecimal emissionKg, LocalDateTime calculatedAt, String calculationStatus, String calculationType) {
        this.carbonActivityId = carbonActivityId;
        this.emissionKg = emissionKg;
        this.calculatedAt = calculatedAt;
        this.calculationStatus = calculationStatus;
        this.calculationType = calculationType;
    }

    public String getCarbonActivityId() {
        return carbonActivityId;
    }

    public void setCarbonActivityId(String carbonActivityId) {
        this.carbonActivityId = carbonActivityId;
    }

    public BigDecimal getEmissionKg() {
        return emissionKg;
    }

    public void setEmissionKg(BigDecimal emissionKg) {
        this.emissionKg = emissionKg;
    }

    public LocalDateTime getCalculatedAt() {
        return calculatedAt;
    }

    public void setCalculatedAt(LocalDateTime calculatedAt) {
        this.calculatedAt = calculatedAt;
    }

    public String getCalculationStatus() {
        return calculationStatus;
    }

    public void setCalculationStatus(String calculationStatus) {
        this.calculationStatus = calculationStatus;
    }

    public String getCalculationType() {
        return calculationType;
    }

    public void setCalculationType(String calculationType) {
        this.calculationType = calculationType;
    }
}
