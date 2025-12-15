package com.alwi.carbontracker.repository;

import com.alwi.carbontracker.dto.response.DailyEmissionSummaryDTO;
import com.alwi.carbontracker.model.CarbonEmissionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CarbonEmissionResultRepository extends JpaRepository<CarbonEmissionResult, UUID> {


    @Query(value = """
            SELECT 
                ca.activity_type               AS activityType,
                SUM(cer.emission_kg)           AS totalEmissionKg,
                COUNT(*)                       AS totalEvent,
                SUM(CASE WHEN cer.calculation_status = 'SUCCESS' THEN 1 ELSE 0 END) AS successCount,
                SUM(CASE WHEN cer.calculation_status = 'FAILED' THEN 1 ELSE 0 END)  AS failedCount
            FROM carbon_emission_result cer
            JOIN carbon_activity ca 
                ON cer.carbon_activity_id = ca.id
            WHERE DATE(cer.calculated_at) = :date
            GROUP BY ca.activity_type
            """,
            nativeQuery = true)
    List<DailyEmissionSummaryDTO> getDailySummary(
            @Param("date") LocalDate date
    );


}
