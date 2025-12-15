package com.alwi.carbontracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "emission_factor")
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "emissionFactor", procedureName = "emissionFactor")})

public class EmissionFactor {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "activity_type", nullable = false, unique = true, length = 50)
    private String activityType;


    @Column(name = "unit", nullable = false, length = 20)
    private String unit;

    @Column(name = "factor_kg", nullable = false, precision = 10, scale = 4)
    private BigDecimal factorKg;

    @CreationTimestamp
    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    public EmissionFactor() {
    }

    public EmissionFactor(UUID id, String activityType, String unit, BigDecimal factorKg, LocalDate effectiveFrom) {
        this.id = id;
        this.activityType = activityType;
        this.unit = unit;
        this.factorKg = factorKg;
        this.effectiveFrom = effectiveFrom;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
}
