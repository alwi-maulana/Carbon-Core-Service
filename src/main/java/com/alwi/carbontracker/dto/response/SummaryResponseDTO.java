package com.alwi.carbontracker.dto.response;

import com.alwi.carbontracker.dto.request.DateDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryResponseDTO implements Serializable {

    private BigDecimal totalEmission;

    private Long totalEvent;

    private Long totalSuccess;

    private Long totalFailed;

    private DateDTO date;

    private List<DailyEmissionSummaryDTO> data;

    public SummaryResponseDTO(BigDecimal totalEmission, Long totalEvent, Long totalSuccess, Long totalFailed, DateDTO date, List<DailyEmissionSummaryDTO> data) {
        this.totalEmission = totalEmission;
        this.totalEvent = totalEvent;
        this.totalSuccess = totalSuccess;
        this.totalFailed = totalFailed;
        this.date = date;
        this.data = data;
    }

    public List<DailyEmissionSummaryDTO> getData() {
        return data;
    }

    public void setData(List<DailyEmissionSummaryDTO> data) {
        this.data = data;
    }

    public Long getTotalSuccess() {
        return totalSuccess;
    }

    public void setTotalSuccess(Long totalSuccess) {
        this.totalSuccess = totalSuccess;
    }

    public Long getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(Long totalFailed) {
        this.totalFailed = totalFailed;
    }

    public DateDTO getDate() {
        return date;
    }

    public void setDate(DateDTO date) {
        this.date = date;
    }

    public BigDecimal getTotalEmission() {
        return totalEmission;
    }

    public void setTotalEmission(BigDecimal totalEmission) {
        this.totalEmission = totalEmission;
    }

    public Long getTotalEvent() {
        return totalEvent;
    }

    public void setTotalEvent(Long totalEvent) {
        this.totalEvent = totalEvent;
    }
}
