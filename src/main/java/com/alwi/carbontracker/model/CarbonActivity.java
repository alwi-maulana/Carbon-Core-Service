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
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "carbon_activity")
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name = "carbonActivity", procedureName = "carbonActivity")})
public class CarbonActivity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID carbonActivityId;

    @Column(name = "activity_type", nullable = false, length = 50)
    private String activityType;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "activity_time", nullable = false)
    private LocalDateTime activityTime;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public CarbonActivity(UUID carbonActivityId, String activityType, BigDecimal amount, LocalDateTime activityTime, String createdBy, LocalDateTime createdAt) {
        this.carbonActivityId = carbonActivityId;
        this.activityType = activityType;
        this.amount = amount;
        this.activityTime = activityTime;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public CarbonActivity() {

    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "CarbonActivity{" + "createdAt=" + createdAt + ", carbonActivityId=" + carbonActivityId + ", activityType='" + activityType + '\'' + ", amount=" + amount + ", activityTime=" + activityTime + ", createdBy='" + createdBy + '\'' + '}';
    }
}
