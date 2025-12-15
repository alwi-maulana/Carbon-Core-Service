package com.alwi.carbontracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "carbon_emission_result")
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "carbonEmissionResult", procedureName = "carbonEmissionResult")})

public class CarbonEmissionResult {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carbon_activity_id", nullable = false)
    private CarbonActivity carbonActivity;

    @Column(name = "emission_kg", nullable = false, precision = 12, scale = 4)
    private BigDecimal emissionKg;

    @CreationTimestamp
    @Column(name = "calculated_at", nullable = false, updatable = false)
    private LocalDateTime calculatedAt;

    @Column(name = "calculation_status", length = 50)
    private String calculationStatus;


    public CarbonEmissionResult(UUID id, CarbonActivity carbonActivity, BigDecimal emissionKg, LocalDateTime calculatedAt, String calculationStatus) {
        this.id = id;
        this.carbonActivity = carbonActivity;
        this.emissionKg = emissionKg;
        this.calculatedAt = calculatedAt;
        this.calculationStatus = calculationStatus;
    }

    public CarbonEmissionResult() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CarbonActivity getCarbonActivity() {
        return carbonActivity;
    }

    public void setCarbonActivity(CarbonActivity carbonActivity) {
        this.carbonActivity = carbonActivity;
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
}
