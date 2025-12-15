package com.alwi.carbontracker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmissionFactorDTO implements Serializable {

    private String activityType;

    private String unit;

    private BigDecimal factorKg;

    private String effectiveFrom;

    public EmissionFactorDTO() {
    }

    public EmissionFactorDTO(String activityType, String unit, BigDecimal factorKg, String effectiveFrom) {
        this.activityType = activityType;
        this.unit = unit;
        this.factorKg = factorKg;
        this.effectiveFrom = effectiveFrom;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
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

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
}
