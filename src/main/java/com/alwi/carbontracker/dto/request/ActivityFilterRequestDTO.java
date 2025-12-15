package com.alwi.carbontracker.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityFilterRequestDTO implements Serializable {

    private LocalDate activityFrom;

    private LocalDate activityTo;

    private String activityType;

    private String status;

    public ActivityFilterRequestDTO(LocalDate activityFrom, LocalDate activityTo, String activityType, String status) {
        this.activityFrom = activityFrom;
        this.activityTo = activityTo;
        this.activityType = activityType;
        this.status = status;
    }

    public LocalDate getActivityFrom() {
        return activityFrom;
    }

    public void setActivityFrom(LocalDate activityFrom) {
        this.activityFrom = activityFrom;
    }

    public LocalDate getActivityTo() {
        return activityTo;
    }

    public void setActivityTo(LocalDate activityTo) {
        this.activityTo = activityTo;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ActivityFilterRequestDTO{" +
                "activityFrom=" + activityFrom +
                ", activityTo=" + activityTo +
                ", activityType='" + activityType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
